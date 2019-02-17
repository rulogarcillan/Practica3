package tuppersoft.com.weather.core.platform

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import tuppersoft.com.weather.core.extensions.loadFromUrl

@BindingAdapter("avatar")
fun avatar(view: ImageView, url: String?) {
    view.loadFromUrl(url)
}