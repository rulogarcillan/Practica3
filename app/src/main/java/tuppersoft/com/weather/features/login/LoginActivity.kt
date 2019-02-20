package tuppersoft.com.weather.features.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import tuppersoft.com.data.repositories.PreferencesRepository
import tuppersoft.com.data.usescases.SaveUser
import tuppersoft.com.domain.dtos.User
import tuppersoft.com.weather.App
import tuppersoft.com.weather.R
import tuppersoft.com.weather.core.platform.GlobalActivity
import tuppersoft.com.weather.core.platform.GlobalConstants
import tuppersoft.com.weather.core.platform.GlobalFunctions
import tuppersoft.com.weather.features.main.MainActivity


class LoginActivity : GlobalActivity() {

    var mGoogleSignInClient: GoogleSignInClient = GlobalFunctions.getGoogleSignInClient(App.instance)
    lateinit var layout: View

    companion object {
        const val PATH = "asset:///"
        const val VIDEO_NAME = "thun.mp4"
        const val GOOGLE_SING = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(tuppersoft.com.weather.R.layout.activity_login)
        layout = findViewById(R.id.layout)
        configThemeBar()
        initExoPlayer()
        if (isLogin()) goMainActivity()
    }

    override fun onResume() {
        super.onResume()
        idPlayerView.player.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        idPlayerView.player.playWhenReady = false
    }

    private fun configThemeBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun initExoPlayer() {
        idPlayerView.player = ExoPlayerFactory.newSimpleInstance(this)
        idPlayerView.player.repeatMode = Player.REPEAT_MODE_ALL

        val dataSourceFactory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, getString(tuppersoft.com.weather.R.string.app_name))
        )

        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(
                Uri
                    .parse("$PATH$VIDEO_NAME")
            )

        (idPlayerView.player as SimpleExoPlayer).prepare(videoSource)
    }


    fun onClickGoogle(view: View) {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SING)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SING) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignIn(task)
        }
    }

    private fun handleGoogleSignIn(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val myUser =
                User(account?.id!!, account.displayName!!, account.email!!, account.photoUrl.toString())

            SaveUser.newInstance().invoke(SaveUser.Params(App.instance, myUser)) { it.either(::handleFailure, ::handleSaveUSer) }

        } catch (e: ApiException) {
            if (e.statusCode != GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                showError(getString(R.string.generic_server_error))
            }
        }
    }

    private fun handleSaveUSer(user: User) {
        PreferencesRepository.savePreference(this, GlobalConstants.USER_ID, user.userId)
        goMainActivity()
    }

    private fun isLogin() = (GoogleSignIn.getLastSignedInAccount(this) != null)

    private fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finish()
    }

}









