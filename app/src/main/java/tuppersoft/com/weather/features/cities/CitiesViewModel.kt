package tuppersoft.com.weather.features.cities

import android.app.Application
import androidx.lifecycle.MutableLiveData
import tuppersoft.com.data.repositories.PreferencesRepository
import tuppersoft.com.data.usescases.AddFavoriteCity
import tuppersoft.com.domain.dtos.City
import tuppersoft.com.weather.App
import tuppersoft.com.weather.core.platform.GlobalConstants
import tuppersoft.com.weather.core.platform.GlobalContextViewModel

class CitiesViewModel(val app: Application) : GlobalContextViewModel(app) {

    var citiesLiveData: MutableLiveData<MutableList<City>> = MutableLiveData(ArrayList())
    private var userId: String = PreferencesRepository.loadPreference(app, GlobalConstants.USER_ID, "") as String

    fun addCity(idZip: String) {
        val params = AddFavoriteCity.Params(App.instance, userId, idZip)

        AddFavoriteCity.newInstance().invoke(params) {
            it.either(::handleFailure, ::handleCity)
        }
    }

    private fun handleCity(city: City) {
        if (citiesLiveData.value?.contains(city) == false) {
            citiesLiveData.value?.add(city)
        }
    }
}