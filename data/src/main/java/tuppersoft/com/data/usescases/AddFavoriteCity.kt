package tuppersoft.com.data.usescases

import android.app.Application
import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure
import tuppersoft.com.domain.dtos.City

class AddFavoriteCity private constructor() : UseCase<City, AddFavoriteCity.Params>() {

    private val repositoryWeather = WeatherRepository.Network()

    companion object {
        fun newInstance(): AddFavoriteCity = AddFavoriteCity()
    }

    override suspend fun run(params: AddFavoriteCity.Params): Either<Failure, City> {

        lateinit var ret: Either<Failure, City>

        ret = repositoryWeather.getCityByZipPostal(params.zipCode)
        if (ret.isRight) {
            ret = repositoryWeather.saveCity(params.app, params.userId ?: "", ret.getValue() as City)
        }
        return ret
    }

    data class Params(val app: Application, val userId: String?, val zipCode: String)


}