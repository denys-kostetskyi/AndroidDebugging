package com.denyskostetskyi.debugging.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denyskostetskyi.debugging.DebuggingApplication
import com.denyskostetskyi.debugging.R
import com.denyskostetskyi.debugging.databinding.FragmentCrashBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import kotlin.random.Random

class CrashFragment : LoggingFragment() {
    override val logTag = "CrashFragment"
    override val logMethod: (tag: String, msg: String) -> Int = Log::v

    private var _binding: FragmentCrashBinding? = null
    private val binding get() = _binding!!
    private val analytics = DebuggingApplication.firebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.ITEM_ID, logTag)
        }
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        binding.buttonCauseRandomCrash.setOnClickListener {
            causeRandomCrash()
        }
    }

    private fun causeRandomCrash() {
        when (Random.nextInt(3)) {
            0 -> throwNullPointerException()
            1 -> modifyUiOnBackgroundThread()
            2 -> causeApplicationNotResponding()
        }
    }

    private fun throwNullPointerException() {
        val string: String? = null
        Log.d(logTag, "${string!!.length}")
    }

    private fun modifyUiOnBackgroundThread() {
        Thread {
            binding.buttonCauseRandomCrash.text = getString(R.string.crash)
        }.start()
    }

    private fun causeApplicationNotResponding() {
        while (true) {
            Thread.sleep(SLEEP_DURATION)
            Log.d(logTag, "Blocking main thread")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SLEEP_DURATION = 1000L
    }
}
