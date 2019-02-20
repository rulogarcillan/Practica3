package tuppersoft.com.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tuppersoft.com.data.db.models.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE city_id = :cityId")
    fun findById(cityId: Long): CityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityEntity)

}