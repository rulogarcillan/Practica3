package tuppersoft.com.weather.core.extensions

import android.view.View


fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.post { this.visibility = View.VISIBLE }
}

fun View.invisible() {
    this.post { this.visibility = View.GONE }
}

