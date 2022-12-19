package com.optimasolution.sampleweather.util

import android.app.AlertDialog.Builder
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.optimasolution.sampleweather.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

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
