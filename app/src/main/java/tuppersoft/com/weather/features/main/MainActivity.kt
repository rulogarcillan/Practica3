package tuppersoft.com.weather.features.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.extensions.failure
import tuppersoft.com.weather.core.extensions.viewModel
import tuppersoft.com.weather.core.platform.GlobalActivity

class MainActivity : GlobalActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar()
        initAdapter()
        initTabLayout()
        initViewModel()
    }

    private fun initViewModel() {
        mainViewModel = viewModel {
            failure(failure, ::handleFailure)
        }
    }

    private fun setActionBar() {
        //  setSupportActionBar(toolbar)
    }

    private fun initAdapter() {
        idViewPager.adapter =
            MainPagerAdapter(supportFragmentManager, this, resources.obtainTypedArray(R.array.tabs))
    }

    private fun initTabLayout() {
        idTabLayout.setupWithViewPager(idViewPager, true)
    }

}
