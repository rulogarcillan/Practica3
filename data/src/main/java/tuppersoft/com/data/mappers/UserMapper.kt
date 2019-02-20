package tuppersoft.com.data.mappers

import tuppersoft.com.data.db.models.UserEntity
import tuppersoft.com.domain.dtos.User

fun User.toUserEntity() = UserEntity(userId, email, name, avatar)
fun UserEntity.toUser() = User(userId, email, name, avatar)
