package com.bacl.pimita.utils

import android.util.Log

class LogUtil {
    companion object {
        const val TAG = "BACL_APP"

        fun d(message: String) {
            val ste = Throwable().stackTrace
            val text = "[" + ste[1].fileName + ":" + ste[1].lineNumber + ":" + ste[1].methodName + "()]"
            Log.d(Companion.TAG, text + message)
        }

        fun i(message: String) {
            val ste = Throwable().stackTrace
            val text = "[" + ste[1].fileName + ":" + ste[1].lineNumber + ":" + ste[1].methodName + "()]"
            Log.i(Companion.TAG, text + message)
        }

        fun e(message: String) {
            val ste = Throwable().stackTrace
            val text = "[" + ste[1].fileName + ":" + ste[1].lineNumber + ":" + ste[1].methodName + "()]"
            Log.e(Companion.TAG, text + message)
        }
    }
}