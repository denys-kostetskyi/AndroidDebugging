package com.denyskostetskyi.debugging

import android.app.Application
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics

class DebuggingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    companion object {
        private const val TAG = "DebuggingApplication"
        lateinit var firebaseAnalytics: FirebaseAnalytics
    }
}
