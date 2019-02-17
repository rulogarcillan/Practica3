package tuppersoft.com.weather.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import tuppersoft.com.weather.R
import tuppersoft.com.weather.databinding.FragmentProfileBinding
import tuppersoft.com.weather.features.main.MainActivity


class ProfileFragment : Fragment() {


    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater, R.layout.fragment_profile, container, false
        )
        val rootView = binding.root
        binding.mainViewModel = (activity as MainActivity).mainViewModel
        //(activity as MainActivity).mainViewModel.userLiveData.observe(this, Observer(this::handleUser))

        return rootView
    }

}