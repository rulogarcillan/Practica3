package tuppersoft.com.data.mappers

import tuppersoft.com.data.db.models.UsersEntity
import tuppersoft.com.domain.Dtos.User

fun User.toUserEntity() = UsersEntity(userId, email, name, avatar)
fun UsersEntity.toUser() = User(userId, email, name, avatar)
