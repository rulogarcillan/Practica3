package tuppersoft.com.data.usescases

import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.domain.dtos.City

class GetCityByZip private constructor(): UseCase<City, GetCityByZip.Params>() {

    private val repository = WeatherRepository.Network()

    companion object {
        fun newInstance(): GetCityByZip = GetCityByZip()
    }

    override fun run(params: Params) = repository.getCityByZipPostal(params.zipCode)

    data class Params(val zipCode: String)
}
