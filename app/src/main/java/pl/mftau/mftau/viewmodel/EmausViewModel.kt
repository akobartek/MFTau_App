package pl.mftau.mftau.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.mftau.mftau.R
import pl.mftau.mftau.db.entities.DrawEntity
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.repositories.EmausRepository
import pl.mftau.mftau.model.repositories.FirebaseRepository
import java.lang.StringBuilder
import java.util.*
import kotlin.random.Random

class EmausViewModel(private val app: Application) : AndroidViewModel(app) {

    companion object {
        const val PREFERENCES_NAME = "drawPrefs"
        const val PREFERENCES_ODD_PERSON_ID = "oddPersonID"
    }

    private val mEmausRepository = EmausRepository(app)
    private val mFirebaseRepository = FirebaseRepository(app)

    private var mAllDatabaseMembers: LiveData<List<MemberEntity>>
    private var mLastDraws: LiveData<List<String>>

    var members: List<MemberEntity>? = null
    var draws: List<String>? = null

    init {
        mAllDatabaseMembers = mEmausRepository.getAllMembers()
        mLastDraws = mEmausRepository.getLastDraws()
    }


    fun getAllMembersFromDatabase(): LiveData<List<MemberEntity>> = mAllDatabaseMembers

    fun getAllMembersFromFirebase(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

    fun getLastDrawsFromDatabase(): LiveData<List<String>> = mLastDraws

    fun getMemberNameByIdFromDatabase(id: String): String = mEmausRepository.getMemberNameById(id)

    fun insertMembersToDatabase(members: List<MemberEntity>) = mEmausRepository.insertMembers(members)

    fun getMaxNumberOfDraw(): Int = mEmausRepository.getMaxNumberOfDraw()

    fun updateMembersListsInDatabase(members: List<MemberEntity>) = mEmausRepository.updateMembersLists(members)

    fun deleteMembersInDatabase(members: List<MemberEntity>) = mEmausRepository.deleteMembers(members)

    fun insertDrawToDatabase(draw: DrawEntity) = mEmausRepository.insertDraw(draw)

    fun deleteLastDrawInDatabase() {
        mEmausRepository.deleteLastDraw()
        setOddPersonID("")
    }

    fun deleteAllDrawsInDatabase() {
        mEmausRepository.deleteAllDraws()
        if (members != null) {
            members!!.forEach { it.drawsList = arrayListOf() }
            updateMembersListsInDatabase(members!!)
        }
        setOddPersonID("")
    }


    fun startDraw(numberOfTry: Int = 0): Boolean {
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
                    Log.d("startDraw", "checkIfDrawIsPossibleNumber value: $checkIfDrawIsPossibleNumber")
                    Log.d("startDraw", "numberOftry value: $numberOfTry")
                    if (checkIfDrawIsPossibleNumber == 20) {
                        return if (numberOfTry == 40) {
                            false
                        } else {
                            startDraw(numberOfTry + 1)
                        }
                    }
                } while (members!!.any { it.id == draw.first && it.drawsList.contains(draw.second) })
                listOfMembersIDs.remove(draw.first)
                listOfMembersIDs.remove(draw.second)
                listOfPairs.add(draw)
            }
        } catch (exc: Exception) {
            Log.e("Przykra sprawa", exc.toString())
            return false
        }

        if (listOfMembersIDs.size % 2 != 0) {
            Log.d("startDraw", "oddPersonId value: " + listOfMembersIDs[0])
            setOddPersonID(listOfMembersIDs[0])
        }

        val listOfDraws = arrayListOf<String>()
        listOfPairs.forEach { pair ->
            members!!.single { it.id == pair.first }.drawsList.add(pair.second)
            members!!.single { it.id == pair.second }.drawsList.add(pair.first)
            listOfDraws.add(members!!.single { it.id == pair.first }.id + "+" + members!!.single { it.id == pair.second }.id)
        }
        val draw = DrawEntity(numberOfDraw = numberOfDraw, draws = listOfDraws.toList(), drawDate = Calendar.getInstance().time)
        Log.d("startDraw", "draw value: $draw")
        insertDrawToDatabase(draw)
        updateMembersListsInDatabase(members!!)

        return true
    }

    private fun singleDraw(listOfIds: MutableList<String>): Pair<String, String> {
        val firstID = listOfIds[Random.nextInt(listOfIds.size)]
        val secondID = listOfIds[Random.nextInt(listOfIds.size)]
        if (firstID == secondID)
            return singleDraw(listOfIds)

        return Pair(firstID, secondID)
    }

    fun copyDrawsToClipboard() {
        if (draws == null) return

        val textToCopy = StringBuilder()
        draws!!.forEach { draw ->
            textToCopy.append("${members!!.single { it.id == draw.substring(0, draw.indexOf("+")) }.name} + " +
                    "${members!!.single { it.id == draw.substring(draw.indexOf("+") + 1, draw.length) }.name}\n"
            )
        }

        val oddPersonID = getOddPersonID()
        if (oddPersonID != null && oddPersonID != "")
            textToCopy.append("\n${getMemberNameByIdFromDatabase(oddPersonID)} ${app.getString(R.string.not_drawn)}")

        (app.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
                .primaryClip = ClipData.newPlainText("emaus", textToCopy.toString())
    }

    fun getOddPersonID(): String? {
        return app.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(PREFERENCES_ODD_PERSON_ID, "")
    }

    fun setOddPersonID(id: String) {
        app.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(PREFERENCES_ODD_PERSON_ID, id)
                .apply()
    }
}