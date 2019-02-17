package tuppersoft.com.data.usescases

import android.app.Application
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import tuppersoft.com.data.repositories.UsersRepository
import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.domain.Dtos.User
import tuppersoft.com.domain.Dtos.WeatherCity
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {

        doAsync {
            val result = run(params)
            uiThread {
                onResult(result)
            }
        }
    }

    class None
}


class SaveUser(private val userRepository: UsersRepository.Network) : UseCase<User, SaveUser.Params>() {

    override fun run(params: Params) = userRepository.saveUser(params.app, params.user)

    data class Params(val app: Application, val user: User)
}


class GetUser(private val userRepository: UsersRepository.Network) : UseCase<User, GetUser.Params>() {

    override fun run(params: Params) = userRepository.getUser(params.app, params.userId)

    data class Params(val app: Application, val userId: String)
}


class GetCityByZip(private val weatherRepository: WeatherRepository.Network) :
    UseCase<WeatherCity, GetCityByZip.Params>() {

    override fun run(params: Params) = weatherRepository.getCityByZipPostal(params.zipCode)

    data class Params(val zipCode: String)
}

