package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import pl.mftau.mftau.R
import pl.mftau.mftau.db.entities.DrawEntity
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.model.local_db.Meeting
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.model.local_db.Retreat
import pl.mftau.mftau.model.local_db.repositories.EmausRepository
import pl.mftau.mftau.model.local_db.repositories.FirebaseRepository
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.showNoInternetDialogWithTryAgain
import java.io.InputStream
import java.util.*
import kotlin.collections.HashMap
import kotlin.random.Random

class MainViewModel(val app: Application) : AndroidViewModel(app) {

    // region General values
    private val mFirebaseRepository = FirebaseRepository(app)
    private val mEmausRepository = EmausRepository(app)

    companion object {
        const val USER_TYPE_ADMIN = 3
        const val USER_TYPE_LEADER = 2
        const val USER_TYPE_MEMBER = 1
        const val USER_TYPE_NONE = 0
    }

    var currentUserType = USER_TYPE_NONE
    // endregion General values

    // region LoginFragment
    private val adminAddresses = arrayOf(
        "rada@mftau.pl", "referat@mftau.pl", "lider@mftau.pl", "sokolowskijbartek@gmail.com"
    )

    fun createUserValues(email: String): HashMap<String, Any> {
        val user = HashMap<String, Any>()
        user[FirestoreUtils.firestoreKeyEmail] = FirebaseAuth.getInstance().currentUser!!.email!!
        when {
            adminAddresses.contains(email) -> {
                user[FirestoreUtils.firestoreKeyIsAdmin] = true
                user[FirestoreUtils.firestoreKeyIsLeader] = false
                user[FirestoreUtils.firestoreKeyIsMember] = false
            }
            email.contains("@mftau.pl") && email != "modlitwa@mftau.pl" -> {
                user[FirestoreUtils.firestoreKeyIsAdmin] = false
                user[FirestoreUtils.firestoreKeyIsLeader] = true
                user[FirestoreUtils.firestoreKeyIsMember] = false
            }
            else -> {
                user[FirestoreUtils.firestoreKeyIsAdmin] = false
                user[FirestoreUtils.firestoreKeyIsLeader] = false
                user[FirestoreUtils.firestoreKeyIsMember] = true
            }
        }
        return user
    }
    // endregion LoginFragment

    // TODO() -> Add new ViewModels to keep firebase information in specific ViewModels

    // region Members Module
    fun getAllMembers(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

//    fun getMemberById(memberId: String): LiveData<Member> = mFirebaseRepository.getMemberById(memberId)

    fun addMember(activity: Activity, memberValues: HashMap<String, Any>, filePath: InputStream?) =
        mFirebaseRepository.addMember(activity, memberValues, filePath)

    fun updatePhoto(activity: Activity, memberId: String, filePath: InputStream?) =
        mFirebaseRepository.updateMemberPhoto(activity, memberId, filePath)

    fun updateMember(
        activity: Activity,
        memberId: String,
        memberValues: HashMap<String, Any>,
        filePath: InputStream?
    ) = mFirebaseRepository.updateMember(activity, memberId, memberValues, filePath)


    fun deleteMember(activity: Activity, memberId: String) =
        mFirebaseRepository.deleteMember(activity, memberId)
    // endregion Members Module

    // region Emauses
    fun getAllMembersFromDatabase() = mEmausRepository.getAllMembers()

    fun getAllMembersFromFirebase(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

    fun getLastDrawsFromDatabase() = mEmausRepository.getLastDraws()

    fun getMemberNameByIdFromDatabase(id: String): String =
        runBlocking { withContext(Dispatchers.IO) { mEmausRepository.getMemberNameById(id) } }

    fun insertMembersToDatabase(members: List<MemberEntity>) =
        viewModelScope.launch { mEmausRepository.insertMembers(members) }

    fun getMaxNumberOfDraw(): Int =
        runBlocking { withContext(Dispatchers.IO) { mEmausRepository.getMaxNumberOfDraw() ?: 0 } }

    fun deleteMembersInDatabase(members: List<MemberEntity>) =
        viewModelScope.launch { mEmausRepository.deleteMembers(members) }

    private fun updateMembersListsInDatabase(members: List<MemberEntity>) =
        viewModelScope.launch { mEmausRepository.updateMembersLists(members) }

    private fun insertDrawToDatabase(draw: DrawEntity) =
        viewModelScope.launch { mEmausRepository.insertDraw(draw) }

    fun deleteLastDrawInDatabase(members: List<MemberEntity>?, draws: List<String>?) {
        viewModelScope.launch { mEmausRepository.deleteLastDraw() }
        if (members != null && draws != null) {
            draws.forEach { draw ->
                members.single { it.id == draw.substring(0, draw.indexOf("+")) }
                    .drawsList.remove(draw.substring(draw.indexOf("+") + 1, draw.length))
                members.single { it.id == draw.substring(draw.indexOf("+") + 1, draw.length) }
                    .drawsList.remove(draw.substring(0, draw.indexOf("+")))
            }
            updateMembersListsInDatabase(members)
        }
    }

    fun deleteAllDrawsInDatabase(members: List<MemberEntity>?) {
        viewModelScope.launch { mEmausRepository.deleteAllDraws() }
        if (members != null) {
            members.forEach { it.drawsList = arrayListOf() }
            updateMembersListsInDatabase(members)
        }
    }

    fun startDraw(members: List<MemberEntity>?, numberOfTry: Int = 0): Boolean {
        Log.d("startDraw", "Executing startDraw method.")

        val listOfMembersIDs = members!!.map { it.id }.toMutableList()
        Log.d("startDraw", listOfMembersIDs.toString())

        val numberOfDraw = getMaxNumberOfDraw() + 1

        val listOfPairs = arrayListOf<Pair<String, String>>()

        try {
            while (listOfMembersIDs.size >= 2) {
                var draw: Pair<String, String>
                var checkIfDrawIsPossibleNumber = 0
                do {
                    draw = singleDraw(listOfMembersIDs)
                    Log.d("startDraw", draw.toString())

                    ++checkIfDrawIsPossibleNumber
                    Log.d(
                        "startDraw",
                        "checkIfDrawIsPossibleNumber value: $checkIfDrawIsPossibleNumber"
                    )
                    Log.d("startDraw", "numberOftry value: $numberOfTry")
                    if (checkIfDrawIsPossibleNumber == 20) {
                        return if (numberOfTry == 40) {
                            false
                        } else {
                            startDraw(members, numberOfTry + 1)
                        }
                    }
                } while (members.any { it.id == draw.first && it.drawsList.contains(draw.second) })
                listOfMembersIDs.remove(draw.first)
                listOfMembersIDs.remove(draw.second)
                listOfPairs.add(draw)
            }
        } catch (exc: Exception) {
            Log.e("Przykra sprawa", exc.toString())
            return false
        }

        val oddPersonId =
            if (listOfMembersIDs.size % 2 != 0) {
                Log.d("startDraw", "oddPersonId value: " + listOfMembersIDs[0])
                listOfMembersIDs[0]
            } else ""

        val listOfDraws = arrayListOf<String>()
        listOfPairs.forEach { pair ->
            members.single { it.id == pair.first }.drawsList.add(pair.second)
            members.single { it.id == pair.second }.drawsList.add(pair.first)
            listOfDraws.add(members.single { it.id == pair.first }.id + "+" + members.single { it.id == pair.second }.id)
        }
        val draw = DrawEntity(
            numberOfDraw = numberOfDraw, draws = listOfDraws.toList(),
            drawDate = Calendar.getInstance().time, oddPersonId = oddPersonId
        )
        Log.d("startDraw", "draw value: $draw")
        insertDrawToDatabase(draw)
        updateMembersListsInDatabase(members)

        return true
    }

    private fun singleDraw(listOfIds: MutableList<String>): Pair<String, String> {
        val firstID = listOfIds[Random.nextInt(listOfIds.size)]
        val secondID = listOfIds[Random.nextInt(listOfIds.size)]
        if (firstID == secondID)
            return singleDraw(listOfIds)

        return Pair(firstID, secondID)
    }

    fun copyDrawsToClipboard(members: List<MemberEntity>?, draws: List<String>?) {
        if (draws == null) return

        val textToCopy = StringBuilder()
        draws.forEach { draw ->
            textToCopy.append(
                "${members!!.single { it.id == draw.substring(0, draw.indexOf("+")) }.name} + " +
                        "${
                            members.single {
                                it.id == draw.substring(draw.indexOf("+") + 1, draw.length)
                            }.name
                        }\n"
            )
        }

        val oddPersonId = getOddPersonId()
        if (oddPersonId != null && oddPersonId != "") {
            val oddPersonName = getMemberNameByIdFromDatabase(oddPersonId)
            val genderText =
                if (oddPersonName.substring(0, oddPersonName.indexOf(" ")).last() == 'a')
                    app.getString(R.string.not_drawn_female)
                else
                    app.getString(R.string.not_drawn_male)
            textToCopy.append("\n$oddPersonName $genderText")
        }

        (app.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            .setPrimaryClip(ClipData.newPlainText("emaus", textToCopy.toString()))
    }

    fun getOddPersonId(): String? =
        runBlocking { withContext(Dispatchers.IO) { mEmausRepository.getOddPersonId() } }
    // endregion Emauses

    // region Meetings Module
    fun getAllMeetings(meetingType: Int): LiveData<List<Meeting>> =
        mFirebaseRepository.getAllMeetings(meetingType)

//    fun getMeetingById(meetingId: String, meetingType: Int): LiveData<Meeting> =
//            mFirebaseRepository.getMeetingById(meetingId, meetingType)

    fun clearMeetings() = mFirebaseRepository.clearMeetings()

    fun addMeeting(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>) =
        mFirebaseRepository.addMeeting(activity, meetingType, meetingValues)

    fun addMeetingWithAttendanceList(
        activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>
    ) = mFirebaseRepository.addMeetingWithAttendanceList(activity, meetingType, meetingValues)

    fun updateMeeting(
        activity: Activity, meetingId: String, meetingType: Int, meetingValues: HashMap<String, Any>
    ) = mFirebaseRepository.updateMeeting(activity, meetingId, meetingType, meetingValues)

    fun updateAttendanceList(
        activity: Activity, meetingId: String, meetingType: Int,
        attendanceList: ArrayList<String>, absenceList: HashMap<String, String>
    ) =
        mFirebaseRepository.updateAttendanceList(
            activity, meetingId, meetingType, attendanceList, absenceList
        )

    fun deleteMeeting(activity: Activity, meetingId: String, meetingType: Int) =
        mFirebaseRepository.deleteMeeting(activity, meetingId, meetingType)

    fun getPresence(presence: HashMap<String, Array<Int>>): LiveData<HashMap<String, Array<Int>>> =
        mFirebaseRepository.getPresence(presence)
    // endregion Meetings Module

    // region Retreats Module
    fun getAllRetreats(): LiveData<List<Retreat>> = mFirebaseRepository.getAllRetreats()

    fun addRetreat(activity: Activity, retreatValues: HashMap<String, Any>) =
        mFirebaseRepository.addRetreat(activity, retreatValues)

    fun updateRetreat(activity: Activity, retreatId: String, retreatValues: HashMap<String, Any>) =
        mFirebaseRepository.updateRetreat(activity, retreatId, retreatValues)

    fun deleteRetreat(activity: Activity, retreatId: String, withToast: Boolean) =
        mFirebaseRepository.deleteRetreat(activity, retreatId, withToast)
    // endregion RetreatsModule
}