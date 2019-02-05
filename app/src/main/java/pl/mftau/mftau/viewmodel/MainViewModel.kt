package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import pl.mftau.mftau.R
import pl.mftau.mftau.db.entities.DrawEntity
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.model.repositories.EmausRepository
import pl.mftau.mftau.model.repositories.FirebaseRepository
import pl.mftau.mftau.utils.FirestoreUtils
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
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
    var isNightMode = false

    fun getDateFormatted(date: Date): String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
    // endregion General values

    // region LoginFragment
    private val adminAddresses = arrayOf(
            "rada@mftau.pl", "referat@mftau.pl", "webmaster@mftau.pl", "lider@mftau.pl"
    )

    fun createUnderlinedString(string: String): SpannableString {
        val spannable = SpannableString(string)
        spannable.setSpan(UnderlineSpan(), 0, string.length, 0)
        return spannable
    }

    fun isEmailValid(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String?): Boolean {
        val passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,20})"
        val pattern = Pattern.compile(passwordRegex)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

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

    // region BreviaryFragment
    private var breviaryHtml: Array<String?> = arrayOf(null, null, null, null, null, null, null)

    fun wasBreviaryLoaded(type: Int): Boolean = breviaryHtml[type] != null

    fun loadBreviaryHtml(type: Int, loadingDialog: AlertDialog, webView: WebView, activity: Activity) {
        Thread(Runnable {
            if (!wasBreviaryLoaded(type)) {
                val buildUrlPair = buildUrl(type)
                val document = try {
                    Jsoup.connect(buildUrlPair.first).timeout(30000).get()
                } catch (exc: HttpStatusException) {
                    Jsoup.connect(buildUrlPair.second).timeout(30000).get()
                }
                breviaryHtml[type] = document.select("table").last { it.outerHtml().contains("Psalm ") }
                        .html()

                updateBreviaryHtml(type)
            }

            activity.runOnUiThread {
                loadingDialog.hide()
                webView.loadDataWithBaseURL(null, checkBreviaryNightMode(type),
                        "text/html", "UTF-8", null)
                webView.visibility = View.VISIBLE
                webView.scrollTo(0, 0)
                webView.animate().alpha(1f).duration = 444L
            }
        }).start()
    }

    private fun buildUrl(type: Int): Pair<String, String> {
        val breviaryUrlTypes = arrayOf("wezw", /*"godzczyt",*/ "jutrznia", "modlitwa1",
                "modlitwa2", "modlitwa3", "nieszpory", "kompleta")
        val romanMonths = arrayOf("i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x", "xi", "xii")
        val calendar = Calendar.getInstance()
        val dayInt = calendar.get(Calendar.DAY_OF_MONTH)
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = calendar.get(Calendar.MONTH) + 1
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = calendar.get(Calendar.YEAR).toString().substring(2)

        val url1 = "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/$day$month/${breviaryUrlTypes[type]}.php3"
        val url2 = "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/$day${month}p/${breviaryUrlTypes[type]}.php3"
        return Pair(url1, url2)
    }

    private fun updateBreviaryHtml(type: Int) {
        try {
            if (breviaryHtml[type] != null) {
                breviaryHtml[type] = breviaryHtml[type]!!
                        .replace("<tr><td colspan=2 width=490 class=ww>\n", "")
                        .replace("color=\"red\">", "color=\"brown\">")
                        .replace("color:red", "color:brown")
                        .replace("<img src=\"../../images/dot.gif\" width=\"30\" height=\"9\" border=\"0\" alt=\"\">", "")
                        .replace("<img src=\"../../images/dot4.gif\" width=\"30\" height=\"9\" border=\"0\" alt=\"\">", "")
                        .replace("align=\"center\"", "")
                        .replace("class=\"b\"", "style=\"text-indent:12pt\"")
                        .replace("class=\"c\"", "style=\"text-indent:16pt\"")
                        .replace("style=\"margin-left:15pt\"", "")
                        .replace("style=\"font-size:10pt\"", "")
                        .replace("style=\"font-size:10pt; border: 2px solid navy; background-color:#FAE6D2\"", "")
                        .replace("style=\"font-size:8pt; background-color:#FAE6D2\"", "")
                        .replace("<a href=\"http://premium.brewiarz.pl\">W wersji PREMIUM</a>", "")
                        .replace("<a href=\"../../access.php3\">W wersji PREMIUM</a>", "")
                        .replace("dostępne jest nagranie tej Godziny w formacie MP3.", "")
                        .replace(" znajdziesz tutaj propozycję melodii oraz plik mp3 z jej wykonaniem.", "")
                        .replace(" znajdziesz tutaj link do papieskiej katechezy na temat tego psalmu.", "")
                        .replace(" znajdziesz tutaj link do papieskiej katechezy na temat tej pieśni.", "")
                        .replace("<a href=\"../../appendix/formula.php3\" style=\"font-family:tahoma;\" title=\"Formuły wprowadzenia do Modlitwy Pańskiej\" onmouseover=\"t('Formuły wprowadzenia do Modlitwy Pańskiej');return true\">formułą.</a>", "formułą")
                        .replace("<a href=\"../../appendix/blog.php3\" style=\"font-family:tahoma;\" title=\"Inne formuły błogosławieństw\" onmouseover=\"t('Inne formuły błogosławieństw');return true\">błogosławieństwa.</a>", "błogosławieństwa")
                        .replace("<a href=\"../../appendix/blog.php3\" title=\"Inne formuły błogosławieństw\" style=\"font-family:tahoma;\" onmouseover=\"t('Inne formuły błogosławieństw');return true\">błogosławieństwa.</a>", "błogosławieństwa")
                        .replace("<a href=\"../../appendix/prosby.php3\" onmouseover=\"t('Krótsza forma próśb w Nieszporach');return true\" style=\"font-family:tahoma;\" title=\"Krótsza forma próśb w Nieszporach\">krótszej formy próśb.</a>", "krótszej formy próśb")
                        .replace("<a href=\"../../appendix/akt.php3\" title=\"Formuły aktu pokuty\" onmouseover=\"t('Formuły aktu pokuty');return true\" style=\"font-family:tahoma;\">formuł aktu pokuty.</a>", "formuł aktu pokuty.")
                        .replace("ROZWAŻANIE", "")
                        .replace("KOMENTARZ I MP3", "")
                        .replace("KOMENTARZ", "")
                        .replace("<div align=\"center\"><span style=\"color:brown\">wybierz:</span>", "")
                        .replace("wariant I", "")
                        .replace("|", "")
                        .replace("wariant II", "")
                        .replace("font-size:10pt; ", "")
                        .replaceFirst("<br>", "")
            }
        } catch (exc: Exception) {
            Log.e("BreviaryViewModel", exc.toString())
        }
    }

    private fun checkBreviaryNightMode(type: Int): String? {
        return if (isNightMode) {
            val result = "<html><head>" +
                    "<style type=\"text/css\">body{color: #fff; background-color: #28292e;}" +
                    "</style></head>" +
                    "<body>" +
                    breviaryHtml[type] +
                    "</body></html>"
            result.replace("black", "white")
        } else {
            breviaryHtml[type]
        }
    }
    // endregion BreviaryFragment

    // region Members Module
    fun getAllMembers(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

    fun getMemberById(memberId: String): LiveData<Member> = mFirebaseRepository.getMemberById(memberId)

    fun addMember(activity: Activity, memberValues: HashMap<String, Any>, filePath: InputStream?) =
            mFirebaseRepository.addMember(activity, memberValues, filePath)

    fun updatePhoto(activity: Activity, memberId: String, filePath: InputStream?) =
            mFirebaseRepository.updateMemberPhoto(activity, memberId, filePath)

    fun updateMember(activity: Activity, memberId: String, memberValues: HashMap<String, Any>, filePath: InputStream?) =
            mFirebaseRepository.updateMember(activity, memberId, memberValues, filePath)


    fun deleteMember(activity: Activity, memberId: String) =
            mFirebaseRepository.deleteMember(activity, memberId)

    // endregion Members Module

    // region Emauses
    fun getAllMembersFromDatabase(): LiveData<List<MemberEntity>> = mEmausRepository.getAllMembers()

    fun getAllMembersFromFirebase(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

    fun getLastDrawsFromDatabase(): LiveData<List<String>> = mEmausRepository.getLastDraws()

    fun getMemberNameByIdFromDatabase(id: String): String = mEmausRepository.getMemberNameById(id)

    fun insertMembersToDatabase(members: List<MemberEntity>) = mEmausRepository.insertMembers(members)

    fun getMaxNumberOfDraw(): Int = mEmausRepository.getMaxNumberOfDraw()

    private fun updateMembersListsInDatabase(members: List<MemberEntity>) = mEmausRepository.updateMembersLists(members)

    fun deleteMembersInDatabase(members: List<MemberEntity>) = mEmausRepository.deleteMembers(members)

    private fun insertDrawToDatabase(draw: DrawEntity) = mEmausRepository.insertDraw(draw)

    fun insertOldDraws() {
        val listOfDraws = arrayListOf<String>()
        listOfDraws.add("E3cfodkfDGTJrpBGwmYU+bwd5RmlbRx9qkfxgmPqq")
        listOfDraws.add("eKLwsVkwDLi05RA23VWq+gnIoYLeJKRsDsNWMqxjG")
        listOfDraws.add("mQm1R5sj6KW9moZ96Cds+FmKbvHPXaOOno54QhXwf")
        listOfDraws.add("GavDKvtw9XzeXqj6ls9S+wtAFPm7Y0rXU2CQo6tRs")
        listOfDraws.add("BNu5hc2NqWPwMLCdAvM6+CwHmZ3qHnhzLK2XothF7")
        listOfDraws.add("94UclZJ7Tu585JO1Xqzv+OlSwGiNGcEryGDPmG9ei")
        listOfDraws.add("f7sXdHEv8ufc5A0LOiX6+XroxwZ5GUy97fSfkClJm")
        listOfDraws.add("7it7HxqyxiAA6r7AfrFj+D0XnGdqIue9ONEre93kj")
        listOfDraws.add("I41tB8uVHwzF9ZEMLHOB+ncWCtxgyKedoZfJbpBHG")
        listOfDraws.add("Yeapcz9xEyjiXpUmujuQ+hOXynk7XQkLjIU3Syjef")
        listOfDraws.add("DmMs7jUmDzVWwLgWRXx9+EXTh9lJkpUpif9jwPD1V")
        var draw = DrawEntity(numberOfDraw = 1, draws = listOfDraws.toList(),
                drawDate = Calendar.getInstance().time, oddPersonId = "Ega4k0CfQyhE1q6RpwUH")
        insertDrawToDatabase(draw)

        val listOfDraws2 = arrayListOf<String>()
        listOfDraws2.add("Yeapcz9xEyjiXpUmujuQ+gnIoYLeJKRsDsNWMqxjG")
        listOfDraws2.add("7it7HxqyxiAA6r7AfrFj+BNu5hc2NqWPwMLCdAvM6")
        listOfDraws2.add("94UclZJ7Tu585JO1Xqzv+EXTh9lJkpUpif9jwPD1V")
        listOfDraws2.add("hOXynk7XQkLjIU3Syjef+eKLwsVkwDLi05RA23VWq")
        listOfDraws2.add("XroxwZ5GUy97fSfkClJm+GavDKvtw9XzeXqj6ls9S")
        listOfDraws2.add("f7sXdHEv8ufc5A0LOiX6+DmMs7jUmDzVWwLgWRXx9")
        listOfDraws2.add("mQm1R5sj6KW9moZ96Cds+Ega4k0CfQyhE1q6RpwUH")
        listOfDraws2.add("D0XnGdqIue9ONEre93kj+wtAFPm7Y0rXU2CQo6tRs")
        listOfDraws2.add("ncWCtxgyKedoZfJbpBHG+E3cfodkfDGTJrpBGwmYU")
        listOfDraws2.add("CwHmZ3qHnhzLK2XothF7+bwd5RmlbRx9qkfxgmPqq")
        listOfDraws2.add("I41tB8uVHwzF9ZEMLHOB+FmKbvHPXaOOno54QhXwf")
        draw = DrawEntity(numberOfDraw = 2, draws = listOfDraws2.toList(),
                drawDate = Calendar.getInstance().time, oddPersonId = "OlSwGiNGcEryGDPmG9ei")
        insertDrawToDatabase(draw)
    }

    fun deleteLastDrawInDatabase(members: List<MemberEntity>?, draws: List<String>?) {
        mEmausRepository.deleteLastDraw()
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
        mEmausRepository.deleteAllDraws()
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
                    Log.d("startDraw", "checkIfDrawIsPossibleNumber value: $checkIfDrawIsPossibleNumber")
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
        val draw = DrawEntity(numberOfDraw = numberOfDraw, draws = listOfDraws.toList(),
                drawDate = Calendar.getInstance().time, oddPersonId = oddPersonId)
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
            textToCopy.append("${members!!.single { it.id == draw.substring(0, draw.indexOf("+")) }.name} + " +
                    "${members.single { it.id == draw.substring(draw.indexOf("+") + 1, draw.length) }.name}\n"
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
                .primaryClip = ClipData.newPlainText("emaus", textToCopy.toString())
    }

    fun getOddPersonId(): String? = mEmausRepository.getOddPersonId()
    // endregion Emauses

    // region Meetings Module
    fun getAllMeetings(meetingType: Int): LiveData<List<Meeting>> = mFirebaseRepository.getAllMeetings(meetingType)

    fun getMeetingById(meetingId: String, meetingType: Int): LiveData<Meeting> =
            mFirebaseRepository.getMeetingById(meetingId, meetingType)

    fun clearMeetings() = mFirebaseRepository.clearMeetings()

    fun addMeeting(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>) =
            mFirebaseRepository.addMeeting(activity, meetingType, meetingValues)

    fun addMeetingWithAttendanceList(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>) =
            mFirebaseRepository.addMeetingWithAttendanceList(activity, meetingType, meetingValues)

    fun updateMeeting(activity: Activity, meetingId: String, meetingType: Int, meetingValues: HashMap<String, Any>) =
            mFirebaseRepository.updateMeeting(activity, meetingId, meetingType, meetingValues)

    fun updateAttendanceList(activity: Activity, meetingId: String, meetingType: Int,
                             attendanceList: ArrayList<String>, absenceList: HashMap<String, String>) =
            mFirebaseRepository.updateAttendanceList(activity, meetingId, meetingType, attendanceList, absenceList)

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