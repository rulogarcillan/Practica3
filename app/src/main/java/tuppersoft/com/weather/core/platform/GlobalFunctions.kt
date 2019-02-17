package tuppersoft.com.weather.core.platform

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import tuppersoft.com.weather.R

object GlobalFunctions {
    fun getGoogleSignInClient(app: Application): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(app.getString(R.string.client_id))
            .build()
        return GoogleSignIn.getClient(app, gso)
    }
}