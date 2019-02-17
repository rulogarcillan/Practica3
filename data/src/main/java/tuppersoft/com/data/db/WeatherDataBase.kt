package tuppersoft.com.data.db

import android.app.Application
import androidx.room.Room

object WeatherDataBase {

    private var appDb: AppWeatherDatabase? = null

    fun getDatabase(app: Application): AppWeatherDatabase {
        return if (appDb != null)
            appDb as AppWeatherDatabase
        else {
            appDb = Room.databaseBuilder(
                app,
                AppWeatherDatabase::class.java, "database-weather"
            ).build()
            appDb as AppWeatherDatabase
        }
    }
}