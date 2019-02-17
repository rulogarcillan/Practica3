package tuppersoft.com.weather

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.facebook.stetho.Stetho
import tuppersoft.com.data.BuildConfig

class App : Application(), LifecycleObserver {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}