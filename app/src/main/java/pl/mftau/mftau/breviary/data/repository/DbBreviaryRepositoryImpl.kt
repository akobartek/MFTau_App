package pl.mftau.mftau.breviary.data.repository

import pl.mftau.mftau.breviary.data.db.BreviaryDao
import pl.mftau.mftau.breviary.domain.model.Breviary.BreviaryHtml
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository

class DbBreviaryRepositoryImpl(private val dao: BreviaryDao) : DbBreviaryRepository {
    override suspend fun insertBreviary(entity: BreviaryEntity): Long =
        dao.insertBreviary(entity)

    override suspend fun loadBreviary(type: BreviaryType, date: Long): BreviaryHtml? =
        dao.loadBreviary(date)?.let {entity->
            BreviaryHtml(
                when (type) {
                    BreviaryType.INVITATORY -> entity.invitatory
                    BreviaryType.OFFICE_OF_READINGS -> entity.officeOfReadings
                    BreviaryType.LAUDS -> entity.lauds
                    BreviaryType.MIDMORNING_PRAYER -> entity.prayer1
                    BreviaryType.MIDDAY_PRAYER -> entity.prayer2
                    BreviaryType.MIDAFTERNOON_PRAYER -> entity.prayer3
                    BreviaryType.VESPERS -> entity.vespers
                    BreviaryType.COMPLINE -> entity.compline
                } ?: ""
            )
        }

    override suspend fun clearBreviary(date: Long) =
        dao.clearBreviary(date)
}