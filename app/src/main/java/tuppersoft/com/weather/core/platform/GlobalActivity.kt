package tuppersoft.com.weather.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.domain.Failure
import tuppersoft.com.weather.App
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.di.AppComponent
import tuppersoft.com.weather.core.extensions.ShowToast

abstract class GlobalActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
            (application as App).appComponent
        }
        appComponent.inject(App.instance)
    }

    fun showError(msg: String) {
        this.ShowToast(msg)
    }

    fun handleFailure(failure: Failure) {
        when (failure) {
            Failure.ServerError -> showError(getString(R.string.generic_server_error))
            Failure.DbError -> showError(getString(R.string.generic_db_error))
        }
    }

}