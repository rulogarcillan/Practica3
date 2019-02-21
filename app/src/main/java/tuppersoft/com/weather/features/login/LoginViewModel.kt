package tuppersoft.com.weather.features.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import tuppersoft.com.data.repositories.PreferencesRepository
import tuppersoft.com.data.usescases.SaveUser
import tuppersoft.com.domain.dtos.User
import tuppersoft.com.weather.App
import tuppersoft.com.weather.core.platform.GlobalConstants
import tuppersoft.com.weather.core.platform.GlobalContextViewModel

class LoginViewModel(val app: Application) : GlobalContextViewModel(app) {

    val isLogged: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        isLogged.value = GoogleSignIn.getLastSignedInAccount(app) != null
    }

    fun saveUser(user: User) {
        SaveUser.newInstance().invoke(SaveUser.Params(App.instance, user)) { it.fold(::handleFailure, ::handleSaveUSer) }
    }

    private fun handleSaveUSer(user: User) {
        PreferencesRepository.savePreference(app, GlobalConstants.USER_ID, user.userId)
        isLogged.value = true
    }

}