package com.michaelceley.examples.bottomsheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.michaelceley.examples.bottomsheet.adapter.OnSeasonSelectedListener
import com.michaelceley.examples.bottomsheet.adapter.SeasonAdapter
import com.michaelceley.examples.bottomsheet.fragment.SortBottomSheetDialogFragment
import com.michaelceley.examples.bottomsheet.model.Season
import com.michaelceley.examples.bottomsheet.model.SortMode

class MainActivity : AppCompatActivity(),
    SortBottomSheetDialogFragment.SortModeSelectedListener,
    OnSeasonSelectedListener {

    var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Celey Sluggers"

        val bottomSheet: LinearLayout? = findViewById(R.id.ll_bottom_sheet)
        if(bottomSheet != null) {
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        }

        bottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior?.setPeekHeight(0, true)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED

        val recyclerView: RecyclerView? = findViewById(R.id.rv_seasons)
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView?.adapter = SeasonAdapter(this, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_sort -> showSortDialog()
            R.id.action_stats -> showTeamStats()
            else -> return false
        }
        return true
    }

    private fun showSortDialog() {
        val fragment = SortBottomSheetDialogFragment()
        fragment.show(supportFragmentManager, SortBottomSheetDialogFragment.TAG)
    }

    private fun showTeamStats() {
        startActivity(Intent(this, StatsActivity::class.java))
    }

    override fun onSortModeSelected(sortMode: SortMode) {
        (findViewById<RecyclerView>(R.id.rv_seasons)?.adapter as? SeasonAdapter)?.applySort(sortMode)
    }

    override fun onSeasonSelected(season: Season?) {
        season?.let {
            findViewById<TextView>(R.id.text_season).text = getString(R.string.year, it.year)
            findViewById<TextView>(R.id.text_record).text = getString(R.string.record, it.wins, it.losses)
            findViewById<TextView>(R.id.text_win_rate).text = getString(R.string.win_rate, it.wins / 162.0f)
        }

        bottomSheetBehavior?.run {
            if(state != BottomSheetBehavior.STATE_EXPANDED) {
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}