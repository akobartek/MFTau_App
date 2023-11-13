package pl.mftau.mftau.breviary.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import pl.mftau.mftau.breviary.domain.model.Breviary.BreviaryHtml
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository
import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository

class BreviaryLoadAndSaveUseCase(
    private val webRepository: WebBreviaryRepository,
    private val dbRepository: DbBreviaryRepository
) {

    suspend operator fun invoke(office: String, date: String): Flow<BreviaryEntity?> =
        channelFlow<BreviaryEntity?> {
            var entity = BreviaryEntity(
                date = date.split(".").reversed().joinToString("").toLong()
            )
            val asyncInvitatory = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.INVITATORY, true).first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(invitatory = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(invitatory = null)
                send(entity)
            }
            val asyncOffice = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.OFFICE_OF_READINGS, true)
                        .first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(officeOfReadings = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(officeOfReadings = null)
                send(entity)
            }
            val asyncLauds = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.LAUDS, true).first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(lauds = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(lauds = null)
                send(entity)
            }
            val asyncPrayer1 = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.MIDMORNING_PRAYER, true)
                        .first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(prayer1 = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(prayer1 = null)
                send(entity)
            }
            val asyncPrayer2 = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.MIDDAY_PRAYER, true)
                        .first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(prayer2 = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(prayer2 = null)
            }
            val asyncPrayer3 = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.MIDAFTERNOON_PRAYER, true)
                        .first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(prayer3 = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(prayer3 = null)
            }
            val asyncVespers = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.VESPERS, true).first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(vespers = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(vespers = null)
                send(entity)
            }
            val asyncCompline = async {
                val result =
                    webRepository.loadBreviary(office, date, BreviaryType.COMPLINE, true).first()
                entity =
                    if (result.isSuccess && result.getOrNull() is BreviaryHtml)
                        entity.copy(compline = (result.getOrNull()!! as BreviaryHtml).html)
                    else entity.copy(compline = null)
                send(entity)
            }
            awaitAll(
                asyncInvitatory, asyncOffice, asyncLauds, asyncPrayer1,
                asyncPrayer2, asyncPrayer3, asyncVespers, asyncCompline
            )
            async {
                val id = dbRepository.insertBreviary(entity)
                entity = entity.copy(id = id)
                send(entity)
            }.await()
            channel.close()
            awaitClose { }
        }.catch { emit(null) }
            .flowOn(Dispatchers.IO)
}