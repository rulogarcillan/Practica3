package tuppersoft.com.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "cities", primaryKeys = ["city_id"])
data class CityEntity(
    @ColumnInfo(name = "city_id")
    var cityId: Long,
    @ColumnInfo(name = "name")
    var name: String
)

