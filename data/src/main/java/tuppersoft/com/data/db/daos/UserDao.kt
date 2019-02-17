package tuppersoft.com.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tuppersoft.com.data.db.models.UsersEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE user_id = :userId")
    fun findById(userId: String): UsersEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UsersEntity)

}