package tuppersoft.com.data.mappers

import tuppersoft.com.data.db.models.CityEntity
import tuppersoft.com.domain.dtos.City


fun City.toCityEntity() = CityEntity(id, name)
fun CityEntity.toCity() = City(cityId, name)
