package tuppersoft.com.weather.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders


inline fun <reified T : ViewModel> AppCompatActivity.viewModel(body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}