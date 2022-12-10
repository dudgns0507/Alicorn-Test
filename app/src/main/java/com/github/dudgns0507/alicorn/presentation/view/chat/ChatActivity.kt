package com.github.dudgns0507.alicorn.presentation.view.chat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import androidx.activity.viewModels
import com.github.dudgns0507.alicorn.R
import com.github.dudgns0507.alicorn.core.BaseActivity
import com.github.dudgns0507.alicorn.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatBundle(
    val id: Int
) : Parcelable

@AndroidEntryPoint
class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_chat
    override val vm: ChatViewModel by viewModels()

    private var bundle: ChatBundle? = null

    companion object {
        fun callingIntent(context: Context, bundle: ChatBundle): Intent {
            return Intent(context, ChatActivity::class.java).apply {
                putExtra("bundle", bundle)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        bundle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("bundle", ChatBundle::class.java)
        } else {
            intent.getParcelableExtra<ChatBundle>("bundle")
        }
    }

    override fun initBinding(): Unit = with(binding) {

    }

    override fun initListener(): Unit = with(binding) {

    }

    override fun initObserve(): Unit = with(vm) {

    }

    override fun afterBinding() = Unit
}