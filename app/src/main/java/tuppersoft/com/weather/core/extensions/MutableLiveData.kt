package tuppersoft.com.weather.core.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.addAndRefresh(value: T) {
    val list = this.value ?: arrayListOf()
    list.add(value)
    this.value = list
}