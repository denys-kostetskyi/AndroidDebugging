package com.denyskostetskyi.debugging.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denyskostetskyi.debugging.R
import com.denyskostetskyi.debugging.databinding.FragmentCrashBinding
import kotlin.random.Random

class CrashFragment : Fragment() {
    private var _binding: FragmentCrashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrashBinding.inflate(inflater, container, false)
        Log.v(TAG, "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v(TAG, "onViewCreated")
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
        Log.d(TAG, "${string!!.length}")
    }

    private fun modifyUiOnBackgroundThread() {
        Thread {
            binding.buttonCauseRandomCrash.text = getString(R.string.crash)
        }.start()
    }

    private fun causeApplicationNotResponding() {
        while (true) {
            Thread.sleep(SLEEP_DURATION)
            Log.d(TAG, "Blocking main thread")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v(TAG, "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v(TAG, "onDetach")
    }

    companion object {
        private const val TAG = "CrashFragment"
        private const val SLEEP_DURATION = 1000L
    }
}
