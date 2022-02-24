package teach.meskills.lastfm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class LastFmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}