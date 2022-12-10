package com.github.dudgns0507.alicorn.core

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.github.dudgns0507.alicorn.databinding.WidgetLoadingDialogBinding

class LoadingDialog : DialogFragment() {
    lateinit var cancelListener: DialogInterface.OnCancelListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = WidgetLoadingDialogBinding.inflate(inflater).root

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(false)
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        if (::cancelListener.isInitialized) {
            cancelListener.onCancel(dialog)
        }
    }
}