package tuppersoft.com.data.repositories

import android.app.Application
import arrow.core.Either
import tuppersoft.com.data.db.WeatherDataBase
import tuppersoft.com.data.mappers.toUser
import tuppersoft.com.data.mappers.toUserEntity
import tuppersoft.com.domain.Failure
import tuppersoft.com.domain.dtos.User

interface UsersRepository {

    fun saveUser(app: Application, user: User): Either<Failure, User>
    fun getUser(app: Application, userId: String): Either<Failure, User>

    object Network : UsersRepository {
        override fun saveUser(app: Application, user: User): Either<Failure, User> {
            return try {
                WeatherDataBase.getDatabase(app).userDao().insertUser(user.toUserEntity())
                Either.Right(user)

            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }

        override fun getUser(app: Application, userId: String): Either<Failure, User> {
            return try {
                Either.Right(WeatherDataBase.getDatabase(app).userDao().findById(userId).toUser())

            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }
    }


}