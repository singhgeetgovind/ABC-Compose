package com.singhgeetgovind.abc_compose.util

object Utility {
    fun <T:Any> T.TAG(t:T):String = this::class.simpleName.toString()
    const val TAG = "Utility"
}