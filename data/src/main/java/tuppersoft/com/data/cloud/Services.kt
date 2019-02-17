package tuppersoft.com.data.cloud

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tuppersoft.com.domain.Dtos.WeatherCity


interface Services {

    @GET("/data/2.5/weather")
    fun getCity(@Query("zip") zipCode: String): Call<WeatherCity>
}