package teach.meskills.lastfm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import teach.meskills.lastfm.di.appBaseModule
import teach.meskills.lastfm.di.chartModule
import teach.meskills.lastfm.di.loginModule
import teach.meskills.lastfm.di.trackDetailsModule

class LastFmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LastFmApplication)
            modules(
                appBaseModule,
                loginModule,
                chartModule,
                trackDetailsModule
            )
        }
    }
}