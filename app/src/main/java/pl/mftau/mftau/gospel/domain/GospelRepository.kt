package pl.mftau.mftau.gospel.domain

import pl.mftau.mftau.gospel.domain.model.Gospel

interface GospelRepository {
    fun loadGospel() : Result<Gospel>
}