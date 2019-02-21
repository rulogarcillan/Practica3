package tuppersoft.com.weather.features.cities

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tuppersoft.com.data.repositories.PreferencesRepository
import tuppersoft.com.data.usescases.AddFavoriteCity
import tuppersoft.com.data.usescases.DeleteFavCity
import tuppersoft.com.data.usescases.GetFavoritesCities
import tuppersoft.com.domain.dtos.City
import tuppersoft.com.weather.core.extensions.addAndRefresh
import tuppersoft.com.weather.core.platform.GlobalConstants
import tuppersoft.com.weather.core.platform.GlobalContextViewModel

class CitiesViewModel(val app: Application) : GlobalContextViewModel(app) {

    private var userId: String = PreferencesRepository.loadPreference(app, GlobalConstants.USER_ID, "") as String
    private val _citiesLiveData: MutableLiveData<MutableList<City>> = MutableLiveData(mutableListOf())
    val citiesLiveData: LiveData<MutableList<City>>
        get() = _citiesLiveData


    init {
        getAllFavCities()
    }

    private fun getAllFavCities() {
        val params = GetFavoritesCities.Params(app, userId)
        GetFavoritesCities.newInstance().invoke(params) {
            it.fold(::handleFailure, ::handleCities)
        }
    }

    fun addCity(idZip: String) {
        val params = AddFavoriteCity.Params(app, userId, idZip)
        AddFavoriteCity.newInstance().invoke(params) {
            it.fold(::handleFailure, ::handleCity)
        }
    }

    fun deleteCity(city: City) {
        val params = DeleteFavCity.Params(app, city.id, userId)
        DeleteFavCity.newInstance().invoke(params) {
            it.fold(::handleFailure, ::handleDelete)
        }
    }

    private fun handleDelete(items: Int) {
        if (items > 0) {
            getAllFavCities()
        }
    }

    private fun handleCities(cities: MutableList<City>) {
        _citiesLiveData.value = cities
    }

    private fun handleCity(city: City) {
        if (_citiesLiveData.value?.contains(city) == false) {
            _citiesLiveData.addAndRefresh(city)
        }
    }
}