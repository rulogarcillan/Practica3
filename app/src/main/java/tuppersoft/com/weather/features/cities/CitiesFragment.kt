package tuppersoft.com.weather.features.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.android.synthetic.main.fragment_cities.view.*
import tuppersoft.com.domain.Failure
import tuppersoft.com.domain.dtos.City
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.extensions.failure
import tuppersoft.com.weather.core.extensions.observe
import tuppersoft.com.weather.core.extensions.viewModel
import tuppersoft.com.weather.databinding.FragmentCitiesBinding
import tuppersoft.com.weather.features.main.MainActivity


class CitiesFragment : Fragment(), CitiesAction {


    private lateinit var citiesViewModel: CitiesViewModel
    private lateinit var binding: ViewDataBinding
    lateinit var rootView: View

    companion object {
        fun newInstance() = CitiesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FragmentCitiesBinding>(
            inflater, R.layout.fragment_cities, container, false
        )
        rootView = binding.root
        initViewModel()
        rootView.idSearch.setOnClickListener {
            citiesViewModel.addCity(idZip.text.toString())
        }
        return rootView
    }

    private fun initViewModel() {
        citiesViewModel = (activity as AppCompatActivity).viewModel {
            failure(failure, ::handleFailure)
            observe(citiesLiveData, ::handleGetCities)
        }
    }

    private fun handleFailure(failure: Failure) {
        (activity as MainActivity).handleFailure(failure)
    }


    private fun handleGetCities(cities: MutableList<City>?) {
        if (rootView.idCities.layoutManager == null) {
            rootView.idCities.layoutManager = LinearLayoutManager(activity)
        }
        if (cities != null) {
            rootView.idCities.adapter = CitiesAdapter(this, cities)
        }
    }

    override fun clickDelete(city: City) {
        citiesViewModel.deleteCity(city)
    }
}
