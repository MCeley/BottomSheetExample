package com.michaelceley.examples.bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michaelceley.examples.bottomsheet.fragment.StatsBottomSheetDialogFragment

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = StatsBottomSheetDialogFragment()
        fragment.show(supportFragmentManager, StatsBottomSheetDialogFragment.TAG)
    }
}