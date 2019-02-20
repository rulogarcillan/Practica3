package tuppersoft.com.weather.features.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import tuppersoft.com.data.repositories.PreferencesRepository
import tuppersoft.com.data.usescases.GetUser
import tuppersoft.com.domain.dtos.User
import tuppersoft.com.weather.core.platform.GlobalConstants
import tuppersoft.com.weather.core.platform.GlobalContextViewModel

class MainViewModel(val app: Application) : GlobalContextViewModel(app) {

    var userLiveData: MutableLiveData<User> = MutableLiveData()

    init {
        val userId = PreferencesRepository.loadPreference(app, GlobalConstants.USER_ID, "") as String
        GetUser.newInstance().invoke(GetUser.Params(app, userId)) {
            it.either(::handleFailure, ::handleUser)
        }
    }

    private fun handleUser(user: User) {
        userLiveData.value = user
    }
}