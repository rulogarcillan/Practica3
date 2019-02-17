package tuppersoft.com.weather.features.main

import android.content.Context
import android.content.res.TypedArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import tuppersoft.com.weather.R
import tuppersoft.com.weather.features.cities.CitiesFragment
import tuppersoft.com.weather.features.profile.ProfileFragment

class MainPagerAdapter(fm: FragmentManager, private val context: Context, private val list: TypedArray) :
    FragmentStatePagerAdapter(fm) {

    override fun getCount() = list.length()

    override fun getItem(position: Int): Fragment =
        when (list.getResourceId(position,0)) {
            R.string.tab_today -> DayFragment.newInstance()
            R.string.tab_tomorrow -> DayFragment.newInstance()
            R.string.tab_cities -> CitiesFragment.newInstance()
            R.string.tab_profile -> ProfileFragment.newInstance()
            else -> DayFragment.newInstance()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        context.getString(list.getResourceId(position,0)) ?: context.getString(R.string.app_name)
}