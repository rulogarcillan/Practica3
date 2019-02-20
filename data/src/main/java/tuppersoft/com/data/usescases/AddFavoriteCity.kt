package tuppersoft.com.data.usescases

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import tuppersoft.com.data.repositories.UsersRepository
import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure
import tuppersoft.com.domain.dtos.City
import tuppersoft.com.domain.dtos.User

class AddFavoriteCity private constructor() : UseCase<City, AddFavoriteCity.Params>() {


    private val repository = UsersRepository.Network()
    private val repositoryWeather = WeatherRepository.Network()

    companion object {
        fun newInstance(): AddFavoriteCity = AddFavoriteCity()
    }

    override suspend fun run(params: AddFavoriteCity.Params): Either<Failure, City> {

        lateinit var ret : Either<Failure, City>
        GlobalScope.launch(Dispatchers.IO){
            ret = withContext(Dispatchers.IO) {repositoryWeather.getCityByZipPostal(params.zipCode)}
            if (ret.isRight){
                  ret = repositoryWeather.getCityByZipPostal(params.zipCode)
            }
        }

        return ret

    }

    data class Params(val app: Application, val zipCode: String)


}