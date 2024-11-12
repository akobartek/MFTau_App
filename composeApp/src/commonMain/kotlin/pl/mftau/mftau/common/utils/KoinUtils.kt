package pl.mftau.mftau.common.utils

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import pl.mftau.mftau.auth.di.authModule
import pl.mftau.mftau.breviary.di.breviaryModule
import pl.mftau.mftau.common.di.platformModule
import pl.mftau.mftau.core.di.coreModule
import pl.mftau.mftau.gospel.di.gospelModule
import pl.mftau.mftau.leaders.di.leadersModule
import pl.mftau.mftau.readings.di.readingsModule
import pl.mftau.mftau.songbook.di.songBookModule

private fun getBaseModules() = listOf(
    platformModule,
    coreModule,
    authModule,
    songBookModule,
    breviaryModule,
    gospelModule,
    readingsModule,
    leadersModule,
)

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(getBaseModules())
    }
}