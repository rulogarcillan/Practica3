package tuppersoft.com.data.repositories


import tuppersoft.com.data.cloud.Client
import tuppersoft.com.data.cloud.Services
import tuppersoft.com.data.cloud.cloudRequest
import tuppersoft.com.domain.dtos.City
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure

interface WeatherRepository {

    fun getCityByZipPostal(zipPostal: String): Either<Failure, City>

    class Network : WeatherRepository {
        override fun getCityByZipPostal(zipPostal: String): Either<Failure, City> {
            val zip = "$zipPostal,es"
            return cloudRequest(Client.getRetrofitClient().create(Services::class.java).getCity(zip)) { it }
        }
    }

}