package tuppersoft.com.data.mappers

import tuppersoft.com.data.db.models.CityEntity
import tuppersoft.com.domain.dtos.City


fun City.toCityEntity(userId: String = "") = CityEntity(userId, id, name)
fun CityEntity.toCity() = City(cityId, name)
