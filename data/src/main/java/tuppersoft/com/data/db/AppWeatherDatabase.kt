package tuppersoft.com.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tuppersoft.com.data.db.daos.UserDao
import tuppersoft.com.data.db.models.CityEntity
import tuppersoft.com.data.db.models.UserEntity

@Database(entities = [UserEntity::class, CityEntity::class], version = 1, exportSchema = false)
abstract class AppWeatherDatabase : RoomDatabase() {
    abstract fun usersDao(): UserDao
}
