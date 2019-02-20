package tuppersoft.com.weather.features.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.android.synthetic.main.fragment_cities.view.*
import tuppersoft.com.domain.Failure
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.extensions.failure
import tuppersoft.com.weather.core.extensions.viewModel
import tuppersoft.com.weather.databinding.FragmentCitiesBinding
import tuppersoft.com.weather.features.main.MainActivity


class CitiesFragment : Fragment() {

    lateinit var citiesViewModel: CitiesViewModel

    companion object {
        fun newInstance() = CitiesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentCitiesBinding>(
            inflater, R.layout.fragment_cities, container, false
        )
        val rootView = binding.root
        initViewModel()
        rootView.idSearch.setOnClickListener {
            citiesViewModel.addCity(idZip.text.toString())
        }
        return rootView
    }

    private fun initViewModel() {
        citiesViewModel = (activity as AppCompatActivity).viewModel {
            failure(failure, ::handleFailure)
        }
    }

    private fun handleFailure(failure: Failure) {
        (activity as MainActivity).handleFailure(failure)
    }

    /* private fun handleCity(weatherCity: City) {
         weatherCity.name.log()
     }*/
}
