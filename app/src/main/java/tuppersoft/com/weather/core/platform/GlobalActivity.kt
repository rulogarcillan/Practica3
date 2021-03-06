package tuppersoft.com.weather.core.platform

import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.domain.Failure
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.extensions.ShowToast

abstract class GlobalActivity : AppCompatActivity() {

    fun showError(msg: String) {
        this.ShowToast(msg)
    }

    fun handleFailure(failure: Failure) {
        when (failure) {
            Failure.ServerError -> showError(getString(R.string.generic_server_error))
            Failure.DbError -> showError(getString(R.string.generic_db_error))
            Failure.CodPostalNotFound -> showError(getString(R.string.zippostal_server_error))
        }
    }

}