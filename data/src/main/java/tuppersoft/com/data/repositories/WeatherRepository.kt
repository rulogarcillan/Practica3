package tuppersoft.com.data.repositories


import android.app.Application
import tuppersoft.com.data.cloud.Client
import tuppersoft.com.data.cloud.Services
import tuppersoft.com.data.cloud.cloudRequest
import tuppersoft.com.data.db.WeatherDataBase
import tuppersoft.com.data.mappers.toCityEntity
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure
import tuppersoft.com.domain.dtos.City

interface WeatherRepository {

    fun getCityByZipPostal(zipPostal: String): Either<Failure, City>
    fun saveCity(app: Application, userId: String, city: City): Either<Failure, City>

    class Network : WeatherRepository {

        override fun getCityByZipPostal(zipPostal: String): Either<Failure, City> {
            val zip = "$zipPostal,es"
            return cloudRequest(Client.getRetrofitClient().create(Services::class.java).getCity(zip)) { it }
        }

        override fun saveCity(app: Application, userId: String, city: City): Either<Failure, City> {
            return try {
                Either.Right(WeatherDataBase.getDatabase(app).cityDao().insertCity(city.toCityEntity(userId)))
                Either.Right(city)
            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }
    }

}