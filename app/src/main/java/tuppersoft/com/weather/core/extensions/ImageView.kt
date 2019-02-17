package tuppersoft.com.weather.core.extensions

import android.widget.ImageView
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.platform.GlideApp


fun ImageView.loadFromUrl(url: String?, width: Int? = null, height: Int? = null) {
    if (width != null && height != null) {
        GlideApp.with(context).load(url).placeholder(R.drawable.ic_avatar).override(width, height).into(this)
    } else {
        GlideApp.with(context).load(url).placeholder(R.drawable.ic_avatar).into(this)
    }
}
