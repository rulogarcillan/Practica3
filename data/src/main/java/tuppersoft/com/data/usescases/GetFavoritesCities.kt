package tuppersoft.com.data.usescases

import android.app.Application
import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.domain.dtos.City

class GetFavoritesCities private constructor() : UseCase<MutableList<City>, GetFavoritesCities.Params>() {

    private val repository = WeatherRepository.Network()

    companion object {
        fun newInstance(): GetFavoritesCities = GetFavoritesCities()
    }

    override suspend fun run(params: Params) = repository.getAllCitiesByUser(params.app, params.userId)

    data class Params(val app: Application, val userId: String)
}
