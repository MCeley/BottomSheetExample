package com.michaelceley.examples.bottomsheet

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = StatsBottomSheetDialogFragment()
        fragment.show(supportFragmentManager, StatsBottomSheetDialogFragment.TAG)
    }
}