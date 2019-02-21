package tuppersoft.com.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "cities", primaryKeys = ["user_id", "city_id"])
data class CityEntity(
    @ColumnInfo(name = "user_id")
    var userId: String,
    @ColumnInfo(name = "city_id")
    var cityId: Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "zip_postal")
    var zipPostal: String
)

