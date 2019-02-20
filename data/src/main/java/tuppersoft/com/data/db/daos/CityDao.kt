package tuppersoft.com.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tuppersoft.com.data.db.models.CityEntity
import android.graphics.Picture
import androidx.room.Delete



@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE city_id = :cityId  and user_id = :userId")
    fun findById(cityId: Long, userId: String): CityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityEntity)

    @Delete
    fun deleteCity(city: CityEntity)

}