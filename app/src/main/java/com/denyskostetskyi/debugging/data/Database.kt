package com.denyskostetskyi.debugging.data

import android.content.Context

class Database private constructor(private val context: Context) {

    val value get() = context.isUiContext

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Database(context).also { instance = it }
        }
    }
}