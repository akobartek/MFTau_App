package pl.mftau.mftau.gospel.data

interface GospelRepository {
    fun loadGospel() : Result<Gospel>
}