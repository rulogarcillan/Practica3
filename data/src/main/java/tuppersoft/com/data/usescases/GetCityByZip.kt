package tuppersoft.com.data.usescases

import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.domain.dtos.City

class GetCityByZip private constructor() : UseCase<City, GetCityByZip.Params>() {


    companion object {
        fun newInstance(): GetCityByZip = GetCityByZip()
    }

    override suspend fun run(params: Params) = WeatherRepository.Network.getCityByZipPostal(params.zipCode)

    data class Params(val zipCode: String)
}
