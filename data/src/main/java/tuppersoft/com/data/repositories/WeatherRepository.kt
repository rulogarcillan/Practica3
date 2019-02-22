package tuppersoft.com.data.repositories


import android.app.Application
import arrow.core.Either
import retrofit2.Call
import tuppersoft.com.data.cloud.Client
import tuppersoft.com.data.cloud.Request
import tuppersoft.com.data.cloud.Services
import tuppersoft.com.data.db.WeatherDataBase
import tuppersoft.com.data.mappers.toCity
import tuppersoft.com.data.mappers.toCityEntity
import tuppersoft.com.domain.Failure
import tuppersoft.com.domain.dtos.City

interface WeatherRepository {

    fun getCityByZipPostal(zipPostal: String): Either<Failure, City>
    fun getAllCitiesByUser(app: Application, userId: String): Either<Failure, MutableList<City>>
    fun saveCity(app: Application, userId: String, city: City): Either<Failure, City>
    fun deleteCityById(app: Application,cityId: Long, userId: String): Either<Failure, Int>

    object Network : WeatherRepository, Request {

        override fun getCityByZipPostal(zipPostal: String): Either<Failure, City> {
            val zip = "$zipPostal,es"
            return request(Client.getRetrofitClient().create(Services::class.java).getCity(zip)) { it }
        }

        override fun getAllCitiesByUser(app: Application, userId: String): Either<Failure, MutableList<City>> {
            return try {
                Either.Right(WeatherDataBase.getDatabase(app).cityDao().findByUserId(userId).map { it.toCity() }.toMutableList())
            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }

        override fun saveCity(app: Application, userId: String, city: City): Either<Failure, City> {
            return try {
                WeatherDataBase.getDatabase(app).cityDao().insertCity(city.toCityEntity(userId))
                Either.Right(city)
            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }

        override fun deleteCityById(app: Application,cityId: Long, userId: String): Either<Failure, Int> {
            return try {
                WeatherDataBase.getDatabase(app).cityDao().deleteCity(cityId, userId)
                Either.Right(1)
            } catch (exception: Throwable) {
                Either.Left(Failure.DbError)
            }
        }

        override fun <T, R> request(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body()!!)))
                    false -> {
                        if (response.code() == 404) {
                            Either.Left(Failure.CodPostalNotFound)
                        } else {
                            Either.Left(Failure.ServerError)
                        }
                    }
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }


}