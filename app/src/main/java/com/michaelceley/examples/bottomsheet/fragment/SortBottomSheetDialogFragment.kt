package com.michaelceley.examples.bottomsheet.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.michaelceley.examples.bottomsheet.R
import com.michaelceley.examples.bottomsheet.model.SortMode

class SortBottomSheetDialogFragment() : BottomSheetDialogFragment(), AdapterView.OnItemClickListener {

    companion object {
        const val TAG = "SortBottomSheetDialogFragment"
    }

    private var sortModeSelectedListener: SortModeSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is SortModeSelectedListener) {
            sortModeSelectedListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val list = inflater.inflate(R.layout.fragment_sort_dialog, container, false) as ListView
        list.onItemClickListener = this
        list.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, SortMode.values())
        return list
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val sort = parent?.adapter?.getItem(position) as? SortMode
        sort?.let {
            sortModeSelectedListener?.onSortModeSelected(it)
        }
        dismiss()
    }

    interface SortModeSelectedListener {
        fun onSortModeSelected(sortMode: SortMode)
    }
}