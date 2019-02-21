package tuppersoft.com.weather.features.cities

import tuppersoft.com.domain.dtos.City

interface CitiesAction {

    fun clickDelete(city: City)
}