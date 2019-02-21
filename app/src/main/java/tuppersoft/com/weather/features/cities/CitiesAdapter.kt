package tuppersoft.com.weather.features.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.domain.dtos.City
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.platform.GlobalAdapter

class CitiesAdapter(private val actions: CitiesAction, private val cities: MutableList<City>) : GlobalAdapter<City>(cities) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: tuppersoft.com.weather.databinding.ItemCityBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_city, parent, false
        )
        return CityHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CityHolder).bind(getItem(position), actions)
    }
}

