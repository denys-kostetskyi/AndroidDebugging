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
import androidx.fragment.app.Fragment
import com.denyskostetskyi.debugging.DebuggingApplication
import com.denyskostetskyi.debugging.FirebaseCustomEvents
import com.denyskostetskyi.debugging.R
import com.denyskostetskyi.debugging.data.Database
import com.denyskostetskyi.debugging.databinding.FragmentMemoryLeakBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import java.util.Date


class MemoryLeakFragment : Fragment() {
    private var _binding: FragmentMemoryLeakBinding? = null
    private val binding get() = _binding!!

    private val analytics = DebuggingApplication.firebaseAnalytics
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var database: Database
    private var isBackgroundTaskRunning = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        database = Database.getInstance(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w(TAG, "onCreate")
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.ITEM_ID, TAG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoryLeakBinding.inflate(inflater, container, false)
        initViews()
        Log.w(TAG, "onCreateView")
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.w(TAG, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.w(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w(TAG, "onDestroyView")
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.w(TAG, "onDetach")
    }

    companion object {
        private const val TAG = "MemoryLeakFragment"
        private const val TASK_POST_DELAY = 20000L
    }
}
