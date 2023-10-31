package pl.mftau.mftau.breviary.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import pl.mftau.mftau.breviary.model.Breviary
import java.util.Calendar

class BreviaryRepositoryImpl : BreviaryRepository {

    private val mBreviaryUrlTypes = arrayOf(
        "wezw", "godzczyt", "jutrznia", "modlitwa1",
        "modlitwa2", "modlitwa3", "nieszpory", "kompleta"
    )

    override fun checkIfThereAreMultipleOffices(
        daysFromToday: Int
    ): Flow<Result<Map<String, String>?>> = flow {
        try {
            val document = Jsoup.connect(buildBaseBreviaryUrl(daysFromToday, true) + "index.php3")
                .timeout(15000)
                .get()
            if (!document.html().contains("WYBIERZ OFICJUM", true))
                emit(Result.success(null))
            else {
                val offices = hashMapOf<String, String>()
                val officesDivs = document.select("div")
                    .last { it.html().contains("OFICJUM") }
                    .selectFirst("table")
                    ?.selectFirst("tbody")
                    ?.select("div")
                officesDivs?.forEach {
                    val link = it.selectFirst("a")!!.attr("href").split("/")[1]
                    offices[link] = it.text()
                }
                emit(Result.success(offices))
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            emit(Result.failure(throwable))
        }
    }.flowOn(Dispatchers.IO)

    override fun loadBreviary(
        office: String,
        daysFromToday: Int,
        type: Int
    ): Flow<Result<Breviary>> = flow {
        try {
            val document = Jsoup.connect(buildBaseBreviaryUrl(daysFromToday, office == ""))
                .timeout(30000)
                .get()
            emit(Result.success(object: Breviary() {}))
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            emit(Result.failure(throwable))
        }
    }.flowOn(Dispatchers.IO)

    private fun buildBaseBreviaryUrl(daysFromToday: Int, withDays: Boolean): String {
        val romanMonths =
            arrayOf("i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x", "xi", "xii")
        val calendar = Calendar.getInstance()
        val dayInt = calendar.get(Calendar.DAY_OF_MONTH) + daysFromToday
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = calendar.get(Calendar.MONTH) + 1
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = calendar.get(Calendar.YEAR).toString().substring(2)

        return if (withDays) "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/$day$month/"
        else "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/"
    }
}