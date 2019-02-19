package tuppersoft.com.weather

import android.app.Application
import com.facebook.stetho.Stetho
import tuppersoft.com.data.BuildConfig
import tuppersoft.com.weather.core.di.AppComponent
import tuppersoft.com.weather.core.di.AppModule
import tuppersoft.com.weather.core.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initStetho()
        component.inject(this)
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}