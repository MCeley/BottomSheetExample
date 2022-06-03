package com.michaelceley.examples.bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michaelceley.examples.bottomsheet.fragment.StatsBottomSheetDialogFragment
import com.michaelceley.examples.bottomsheet.fragment.StatsDialogFragment

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isTablet = resources.getBoolean(R.bool.isTablet)

        if(isTablet) {
            val fragment = StatsDialogFragment()
            fragment.show(supportFragmentManager, StatsDialogFragment.TAG)
        } else {
            val fragment = StatsBottomSheetDialogFragment()
            fragment.show(supportFragmentManager, StatsBottomSheetDialogFragment.TAG)
        }
    }
}