package com.denyskostetskyi.debugging.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.denyskostetskyi.debugging.DebuggingApplication
import com.denyskostetskyi.debugging.R
import com.denyskostetskyi.debugging.databinding.FragmentLayoutInspectorBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

class LayoutInspectorFragment : Fragment() {
    private var _binding: FragmentLayoutInspectorBinding? = null
    private val binding get() = _binding!!
    private val analytics = DebuggingApplication.firebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.ITEM_ID, TAG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLayoutInspectorBinding.inflate(inflater, container, false)
        Log.d(TAG, "onCreateView")
        initListView()
        return binding.root
    }

    private fun initListView() {
        val items = List(100) { index -> "Item $index" }
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, R.id.item_text, items)
        binding.listView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}
