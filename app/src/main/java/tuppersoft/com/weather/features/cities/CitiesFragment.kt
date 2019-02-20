package tuppersoft.com.weather.features.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cities.view.*
import tuppersoft.com.data.usescases.GetCityByZip
import tuppersoft.com.domain.dtos.City
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
            GetCityByZip.newInstance().invoke(GetCityByZip.Params(rootView.idZip.text.toString()))
            { it.either((activity as MainActivity)::handleFailure, ::handleCity) }
        }
        return rootView
    }

    private fun handleCity(weatherCity: City) {
        weatherCity.name.log()
    }

}
