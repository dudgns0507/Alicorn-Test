package com.github.dudgns0507.alicorn.core

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    abstract val layoutResId: Int
    abstract val vm: VM

    lateinit var binding: T
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()

        initDataBinding()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.apply {
            lifecycleOwner = this@BaseActivity

            initBinding()
            initListener()
        }
        initLoading()
        initObserve()
        afterBinding()
    }

    open fun onCreate() {}

    abstract fun initObserve()

    abstract fun afterBinding()

    abstract fun initListener()

    abstract fun initBinding()

    private fun initLoading() {
        observe(vm.loading) {
            when (it) {
                true -> showLoading()
                false -> hideLoading()
            }
        }
    }

    fun loading(state: Boolean) {
        vm.loading(state)
    }

    private fun showLoading() {
        if (isFinishing) {
            return
        }

        if (loadingDialog != null && loadingDialog?.isAdded == true) {
            supportFragmentManager.beginTransaction().remove(loadingDialog!!)
        }
        loadingDialog = LoadingDialog().apply {
            cancelListener = DialogInterface.OnCancelListener {
                it.dismiss()
                onBackPressed()
            }
        }
        loadingDialog?.show(supportFragmentManager, null)
    }

    private fun hideLoading() {
        loadingDialog?.dismiss()
    }
}