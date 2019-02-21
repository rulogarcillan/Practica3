package tuppersoft.com.data.db.daos

import androidx.room.*
import tuppersoft.com.data.db.models.CityEntity


@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE city_id = :cityId  and user_id = :userId")
    fun findById(cityId: Long, userId: String): CityEntity

    @Query("SELECT * FROM cities WHERE user_id = :userId")
    fun findByUserId(userId: String): MutableList<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityEntity)

    @Query("DELETE FROM cities  WHERE city_id = :cityId  and user_id = :userId")
    fun deleteCity(cityId: Long, userId: String)

}