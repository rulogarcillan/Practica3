package tuppersoft.com.weather

import android.app.Application
import com.facebook.stetho.Stetho
import tuppersoft.com.data.BuildConfig
import tuppersoft.com.weather.core.di.AppComponent
import tuppersoft.com.weather.core.di.AppModule
import tuppersoft.com.weather.core.di.Arm
import tuppersoft.com.weather.core.di.DaggerAppComponent
import javax.inject.Inject

class App : Application() {


    @Inject
    lateinit var arm: Arm
    companion object {
        lateinit var instance: App
    }

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))

            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent.inject(this)
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}