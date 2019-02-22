package tuppersoft.com.data.usescases

import android.app.Application
import arrow.core.Either
import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.domain.Failure
import tuppersoft.com.domain.dtos.City

class AddFavoriteCity private constructor() : UseCase<City, AddFavoriteCity.Params>() {

    companion object {
        fun newInstance(): AddFavoriteCity = AddFavoriteCity()
    }

    override suspend fun run(params: AddFavoriteCity.Params): Either<Failure, City> {

        lateinit var ret: Either<Failure, City>

        ret = WeatherRepository.Network.getCityByZipPostal(params.zipCode)
        if (ret.isRight()) {
            ret.toOption().orNull()?.zipPostal = params.zipCode
            ret = WeatherRepository.Network.saveCity(params.app, params.userId, ret.toOption().orNull()!!)
        }
        return ret
    }

    data class Params(val app: Application, val userId: String, val zipCode: String)


}