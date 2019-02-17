package tuppersoft.com.weather.core.platform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tuppersoft.com.domain.Failure

abstract class GlobalContextViewModel(app: Application) : AndroidViewModel(app) {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}