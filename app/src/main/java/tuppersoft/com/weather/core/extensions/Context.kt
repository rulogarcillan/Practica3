package tuppersoft.com.weather.core.extensions

import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.ShowToast(msg: String) {
    Toast.makeText(
        this,
        msg,
        Snackbar.LENGTH_LONG
    ).show()
}