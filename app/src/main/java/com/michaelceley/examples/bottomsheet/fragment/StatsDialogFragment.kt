package com.michaelceley.examples.bottomsheet.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.michaelceley.examples.bottomsheet.databinding.FragmentStatsDialogBinding
import com.michaelceley.examples.bottomsheet.viewmodel.StatsViewModel

class StatsDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "StatsDialogFragment"
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        requireActivity().finish()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        requireActivity().finish()
    }

    private lateinit var binding: FragmentStatsDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsDialogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provider = ViewModelProvider(this)
        val viewModel = provider.get(StatsViewModel::class.java)
        binding.viewModel = viewModel
    }
}