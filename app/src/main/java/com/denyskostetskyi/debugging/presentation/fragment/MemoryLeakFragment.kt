package com.denyskostetskyi.debugging.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.denyskostetskyi.debugging.FirebaseCustomEvents
import com.denyskostetskyi.debugging.R
import com.denyskostetskyi.debugging.data.Database
import com.denyskostetskyi.debugging.databinding.FragmentMemoryLeakBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import java.util.Date


class MemoryLeakFragment : LoggingFragment() {
    override val logTag = "MemoryLeakFragment"
    override val logMethod: (tag: String, msg: String) -> Int = Log::i

    private var _binding: FragmentMemoryLeakBinding? = null
    private val binding get() = _binding!!

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var database: Database
    private var isBackgroundTaskRunning = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        database = Database.getInstance(context)
    }

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
        _binding = FragmentMemoryLeakBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.buttonGetValueFromDatabase.setOnClickListener {
            val value = database.value
            analytics.logEvent(FirebaseCustomEvents.Event.ACCESS_DATABASE) {
                param(FirebaseCustomEvents.Param.DATE_TIME, Date().toString())

            }
            Toast.makeText(requireContext(), "Value: $value", Toast.LENGTH_SHORT).show()
        }
        binding.buttonStartBackgroundTask.setOnClickListener { startBackgroundTask() }
    }

    private fun startBackgroundTask() {
        if (!isBackgroundTaskRunning) {
            isBackgroundTaskRunning = handler.postDelayed({
                binding.textViewDelayedTaskResult.text = getString(R.string.delayed_task_finished)
            }, TASK_POST_DELAY)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }

    companion object {
        private const val TASK_POST_DELAY = 20000L
    }
}
