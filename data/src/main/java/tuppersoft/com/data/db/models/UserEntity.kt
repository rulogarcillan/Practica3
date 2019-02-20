package tuppersoft.com.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "users", primaryKeys = ["user_id"])
data class UserEntity(
    @ColumnInfo(name = "user_id")
    var userId: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "url_avatar")
    var avatar: String? = null
)

