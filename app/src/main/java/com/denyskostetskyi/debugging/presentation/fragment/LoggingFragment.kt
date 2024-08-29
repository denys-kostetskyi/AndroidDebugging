package com.denyskostetskyi.debugging.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class LoggingFragment : Fragment() {
    abstract val logTag: String
    abstract val logMethod: (tag: String, msg: String) -> Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logMethod(logTag, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logMethod(logTag, "onCreateView")
        return createView(inflater, container, savedInstanceState)
    }

    abstract fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logMethod(logTag, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        logMethod(logTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        logMethod(logTag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        logMethod(logTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        logMethod(logTag, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logMethod(logTag, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        logMethod(logTag, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        logMethod(logTag, "onDetach")
    }
}
