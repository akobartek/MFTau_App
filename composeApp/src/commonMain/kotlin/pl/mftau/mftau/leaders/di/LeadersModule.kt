package pl.mftau.mftau.leaders.di

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.common.data.MFTauDatabase
import pl.mftau.mftau.leaders.data.DbEmausRepository
import pl.mftau.mftau.leaders.data.FirebaseMeetingsRepository
import pl.mftau.mftau.leaders.data.FirebasePeopleRepository
import pl.mftau.mftau.leaders.domain.db.EmausDao
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository
import pl.mftau.mftau.leaders.domain.usecase.DeleteDrawsUseCase
import pl.mftau.mftau.leaders.domain.usecase.DrawNewEmausesUseCase
import pl.mftau.mftau.leaders.domain.usecase.GetLastDrawUseCase
import pl.mftau.mftau.leaders.presentation.meetings.LeadersMeetingsViewModel
import pl.mftau.mftau.leaders.presentation.presence.LeadersPresenceViewModel
import pl.mftau.mftau.leaders.presentation.emaus.LeadersEmausViewModel
import pl.mftau.mftau.leaders.presentation.people.LeadersPeopleViewModel

val leadersModule = module {
    single<EmausDao> {
        val db = get<MFTauDatabase>()
        db.emausDao()
    }

    factory<PeopleRepository> { FirebasePeopleRepository(get(), get()) }
    viewModel { LeadersPeopleViewModel(get()) }

    factory { DbEmausRepository(get()) }
    factory { GetLastDrawUseCase(get()) }
    factory { DrawNewEmausesUseCase(get()) }
    factory { DeleteDrawsUseCase(get()) }
    viewModel { LeadersEmausViewModel(get(), get(), get(), get()) }

    factory<MeetingsRepository> { FirebaseMeetingsRepository(get(), get()) }
    factory { LeadersMeetingsViewModel(get(), get()) }
    viewModel { LeadersPresenceViewModel(get(), get(), get()) }
}