package com.denyskostetskyi.debugging

import android.app.Application
import android.util.Log

class DebuggingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
    }

    companion object {
        private const val TAG = "DebuggingApplication"
    }
}
