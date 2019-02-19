package tuppersoft.com.weather.core.di

import dagger.Component
import tuppersoft.com.weather.App
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun arm() : Arm

}