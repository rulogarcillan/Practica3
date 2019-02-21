package tuppersoft.com.data.usescases

import android.app.Application
import tuppersoft.com.data.repositories.WeatherRepository

class DeleteFavCity private constructor() : UseCase<Int, DeleteFavCity.Params>() {

    private val repository = WeatherRepository.Network()

    companion object {
        fun newInstance(): DeleteFavCity = DeleteFavCity()
    }

    override suspend fun run(params: Params) = repository.deleteCityById(params.app, params.cityId, params.userId)


    data class Params(val app: Application, val cityId: Long, val userId: String)
}
