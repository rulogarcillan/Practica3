package tuppersoft.com.weather.core.di

import dagger.Module
import dagger.Provides
import tuppersoft.com.weather.App
import javax.inject.Singleton


@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideArmR() = Arm()

}