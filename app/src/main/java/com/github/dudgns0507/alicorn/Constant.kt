package com.github.dudgns0507.alicorn

import com.github.dudgns0507.alicorn.domain.model.UserData

object Constant {
    val isDebug = BuildConfig.DEBUG
    val currentUser = UserData.getSampleData()
}