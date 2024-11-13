package pl.mftau.mftau.breviary.domain.usecases

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import pl.mftau.mftau.breviary.domain.model.Breviary.*
import pl.mftau.mftau.breviary.domain.model.BreviaryDay
import pl.mftau.mftau.breviary.domain.model.BreviaryType.*
import pl.mftau.mftau.breviary.domain.repository.BreviaryRepository

class SaveBreviaryUseCase(private val repository: BreviaryRepository) {
    operator fun invoke(
        office: String,
        date: String,
        accentColor: Color = Color.Unspecified,
    ): Flow<BreviaryDay?> =
        channelFlow<BreviaryDay?> {
            var day = BreviaryDay(date = date)
            val asyncInvitatory = async {
                val result =
                    repository.loadBreviary(office, date, INVITATORY, true, accentColor)
                day = day.copy(invitatory = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            val asyncOffice = async {
                val result =
                    repository.loadBreviary(office, date, OFFICE_OF_READINGS, true, accentColor)
                day = day.copy(officeOfReadings = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            val asyncLauds = async {
                val result =
                    repository.loadBreviary(office, date, LAUDS, true, accentColor)
                day = day.copy(lauds = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            val asyncPrayer1 = async {
                val result =
                    repository.loadBreviary(office, date, MIDMORNING_PRAYER, true, accentColor)
                day = day.copy(prayer1 = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            val asyncPrayer2 = async {
                val result =
                    repository.loadBreviary(office, date, MIDDAY_PRAYER, true, accentColor)
                day = day.copy(prayer2 = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            val asyncPrayer3 = async {
                val result =
                    repository.loadBreviary(office, date, MIDAFTERNOON_PRAYER, true, accentColor)
                day = day.copy(prayer3 = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            val asyncVespers = async {
                val result =
                    repository.loadBreviary(office, date, VESPERS, true, accentColor)
                day = day.copy(vespers = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            val asyncCompline = async {
                val result =
                    repository.loadBreviary(office, date, COMPLINE, true, accentColor)
                day = day.copy(compline = result.getOrNull() as BreviaryHtml?)
                send(day)
            }
            awaitAll(
                asyncInvitatory, asyncOffice, asyncLauds, asyncPrayer1,
                asyncPrayer2, asyncPrayer3, asyncVespers, asyncCompline
            )
            ensureActive()
            async {
                val id = repository.saveBreviary(day)
                day = day.copy(id = id)
                send(day)
            }.await()
            channel.close()
            awaitClose { }
        }.catch { emit(null) }.flowOn(Dispatchers.IO)
}