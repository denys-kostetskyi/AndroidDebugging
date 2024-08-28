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
import com.denyskostetskyi.debugging.data.Database
import com.denyskostetskyi.debugging.databinding.FragmentMemoryLeakBinding


class MemoryLeakFragment : Fragment() {
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
        Log.w(TAG, "onCreate")
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
        binding.buttonGetValueFromDatabase.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val value = database.value
                Toast.makeText(requireContext(), "Value: $value", Toast.LENGTH_SHORT).show()
            }
        })
        binding.buttonStartBackgroundTask.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startBackgroundTask()
            }
        })
    }

    private fun startBackgroundTask() {
        if (!isBackgroundTaskRunning) {
            isBackgroundTaskRunning = handler.postDelayed({
                binding.textViewDelayedTaskResult.text = "Delayed task finished"
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
