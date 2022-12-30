package com.optimasolution.sampleweather.util

import android.app.AlertDialog.Builder
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.optimasolution.sampleweather.R
import com.optimasolution.sampleweather.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun emptyString(): String = ""

fun emptyItemString(): String = "-"

fun String.orItemString(): String {
    return this.ifEmpty { "-" }
}

fun String.orZero(): String {
    return this.ifEmpty { "0" }
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun ArrayList<String>.orEmptyList(): ArrayList<String> {
    return this.ifEmpty { ArrayList() }
}

fun String.orEmptyItemString(): String {
    return this.ifEmpty { "" }
}

fun String.addBearerToken(): String = "Bearer $this"

fun String.toQuery(): String = "eq.$this"

fun showAlertDialog(context: Context, message: String, onPositiveClick: () -> Unit) {
    val builder = Builder(context)
    builder.setMessage(message).setCancelable(true).setPositiveButton("Ya" as CharSequence) { dialog, id ->
        onPositiveClick.invoke()
    }.setNegativeButton("Tidak") { dialog, id ->
        dialog.cancel()
    }
    builder.create().show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun AppCompatActivity.setFragment(fragment: Fragment, fragmentContainer: Int) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(fragmentContainer, fragment)
    fragmentTransaction.commit()
}

fun Context.startIntentWithClearFlags(context: Context, className: Class<*>) {
    val intent = Intent(context, className)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}

fun ProgressDialog.showCancelableDialog(message: String) {
    this.setTitle(message)
    this.setCancelable(true)
    this.show()
}

fun ProgressDialog.showNoCancelableDialog(message: String) {
    this.setTitle(message)
    this.setCancelable(false)
    this.show()
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> CoroutineScope.collectResult(liveData: MutableLiveData<T>, block: suspend () -> Flow<T>) {
    this.launch {
        val result = block.invoke()
        result.collect {
            liveData.postValue(it)
        }
    }
}

fun <T> LiveData<Resource<T>>.observe(
    lifecycleOwner: LifecycleOwner,
    onLoading: () -> Unit,
    onSuccess: (items: T) -> Unit,
    onError: (errorMessage: String) -> Unit,
) {
    observe(lifecycleOwner) {
        when (it) {
            is Resource.Loading<T> -> onLoading.invoke()
            is Resource.Success<T> -> it.data?.let { data -> onSuccess.invoke(data) }
            else -> onError.invoke(it.errorMessage.orEmpty())
        }
    }
}

fun ImageView.setImageWeather(status: String) {
    this.setImageResource(
        when(status) {
            CLOUDY -> R.drawable.ic_cloudy_small
            SUNNY_RAIN -> R.drawable.img_weather_sunny_rain_medium
            RAIN -> R.drawable.ic_rain_small
            STORMY -> R.drawable.ic_stormy_small
            STORMY_RAIN -> R.drawable.ic_stormy_rain_small
            else -> R.drawable.ic_sunny_small
        }
    )
}

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun Date.toString(format: String, locale: Locale = Locale("id", "ID")): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}