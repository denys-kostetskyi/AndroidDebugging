package com.denyskostetskyi.debugging.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.denyskostetskyi.debugging.R
import com.denyskostetskyi.debugging.databinding.FragmentLayoutInspectorBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

class LayoutInspectorFragment : LoggingFragment() {
    override val logTag = "LayoutInspectorFragment"
    override val logMethod: (tag: String, msg: String) -> Int = Log::d

    private var _binding: FragmentLayoutInspectorBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentLayoutInspectorBinding.inflate(inflater, container, false)
        initListView()
        return binding.root
    }

    private fun initListView() {
        val items = List(100) { index -> "Item $index" }
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, R.id.item_text, items)
        binding.listView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
