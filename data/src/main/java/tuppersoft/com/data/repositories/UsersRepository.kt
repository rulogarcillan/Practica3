package tuppersoft.com.data.repositories

import android.app.Application
import tuppersoft.com.data.db.WeatherDataBase
import tuppersoft.com.data.mappers.toUser
import tuppersoft.com.data.mappers.toUserEntity
import tuppersoft.com.domain.Dtos.User
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure

interface UsersRepository {

    fun saveUser(app: Application, user: User): Either<Failure, User>
    fun getUser(app: Application, userId: String): Either<Failure, User>

    class Network : UsersRepository {
        override fun saveUser(app: Application, user: User): Either<Failure, User> {
            return try {
                WeatherDataBase.getDatabase(app).usersDao().insertUser(user.toUserEntity())
                Either.Right(user)

            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }

        override fun getUser(app: Application, userId: String): Either<Failure, User> {
            return try {
                Either.Right(WeatherDataBase.getDatabase(app).usersDao().findById(userId).toUser())

            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }
    }


}