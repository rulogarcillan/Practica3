package tuppersoft.com.weather.core.extensions

import android.util.Log
import tuppersoft.com.weather.BuildConfig

const val TAG = "weather"

fun String?.log(tag: String = TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this ?: "************** - Null value - **************")
    }
}