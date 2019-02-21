package tuppersoft.com.weather.features.cities

import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import tuppersoft.com.domain.dtos.City
import tuppersoft.com.weather.databinding.ItemCityBinding

class CityHolder(private val binding: ItemCityBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(city: City, actions: CitiesAction) {
        binding.city = city

        binding.idDelete.onClick {
            actions.clickDelete(city)
        }

    }

}