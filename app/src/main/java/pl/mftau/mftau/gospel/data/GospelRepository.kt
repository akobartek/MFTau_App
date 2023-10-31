package pl.mftau.mftau.gospel.data

import kotlinx.coroutines.flow.Flow

interface GospelRepository {
    fun loadGospel() : Flow<Result<Gospel>>
}