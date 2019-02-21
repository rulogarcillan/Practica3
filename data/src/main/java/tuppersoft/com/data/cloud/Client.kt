package tuppersoft.com.data.cloud

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tuppersoft.com.data.BuildConfig
import java.util.concurrent.TimeUnit


object Client {

    const val API_KEY = "0345c6edf1c19c92c38c11942c97a432"
    const val PARMS_KEY = "APPID"
    const val BASE_URL = " http://api.openweathermap.org/"

    fun getRetrofitClient(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    getLogLevel()
                )
            )
        }

        okHttpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val original = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder().addQueryParameter(PARMS_KEY, API_KEY).build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })



        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}


