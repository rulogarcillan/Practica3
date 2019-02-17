package tuppersoft.com.weather.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import tuppersoft.com.weather.R

class DayFragment : Fragment() {

    companion object {
        fun newInstance(): DayFragment = DayFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.fragment_day, container, false
        )
        val rootView = binding.root

        return rootView
    }

}