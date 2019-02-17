package tuppersoft.com.weather.features.cities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cities.view.*
import tuppersoft.com.data.repositories.WeatherRepository
import tuppersoft.com.data.usescases.GetCityByZip
import tuppersoft.com.domain.Dtos.WeatherCity
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.extensions.log
import tuppersoft.com.weather.databinding.FragmentCitiesBinding
import tuppersoft.com.weather.features.main.MainActivity


class CitiesFragment : Fragment() {

    companion object {
        fun newInstance() = CitiesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentCitiesBinding>(
            inflater, R.layout.fragment_cities, container, false
        )
        val rootView = binding.root

        rootView.idSearch.setOnClickListener {
            GetCityByZip(WeatherRepository.Network()).invoke(GetCityByZip.Params(rootView.idZip.text.toString()))
            { it.either((activity as MainActivity)::handleFailure, ::handleCity) }
        }
        return rootView
    }

    fun handleCity(weatherCity: WeatherCity){
        weatherCity.name.log()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // arguments?.getString(TITLE) ?: context.getString(R.string.app_name)
    }


}
