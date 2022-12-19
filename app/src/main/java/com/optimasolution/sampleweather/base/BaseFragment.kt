package com.optimasolution.sampleweather.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.optimasolution.sampleweather.R
import org.koin.android.ext.android.inject

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var mBinding: T? = null
    protected val binding
        get() = mBinding!!
    private var loadingDialog: AlertDialog? = null
    private var errorDialog: AlertDialog? = null
    private lateinit var dialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = getViewBinding(inflater, container)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ProgressDialog(requireContext())

        setupIntent()
        setupUI()
        setupAction()
        setupProcess()
        setupObserver()
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun setupIntent()

    abstract fun setupUI()

    abstract fun setupAction()

    abstract fun setupProcess()

    abstract fun setupObserver()

    protected fun dismissDialog() {
        dialog.dismiss()
    }

    protected fun showLoading() {
        loadingDialog?.show()
    }

    protected fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    protected fun showErrorDialog(message: String, onRetry: () -> Unit) {
        errorDialog?.apply {
            this.setTitle(getString(R.string.label_error))
            setMessage(message)
            setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.action_retry)) { dialog, _ ->
                onRetry.invoke()
                dialog.dismiss()
            }
            show()
        }
    }

    protected fun setLoadingDialog(alertDialog: AlertDialog) {
        loadingDialog = alertDialog
    }

    protected fun setErrorDialog(alertDialog: AlertDialog) {
        errorDialog = alertDialog
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}