package com.michaelceley.examples.bottomsheet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.michaelceley.examples.bottomsheet.model.Season

class MainActivity : AppCompatActivity() {

    var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Celey Sluggers"

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.ll_bottom_sheet))

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

        val recyclerView = findViewById<RecyclerView>(R.id.rv_seasons)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = SeasonAdapter(this)
    }

    fun showDrawerWithSeasonData(season: Season?) {
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

    inner class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textYear = itemView.findViewById<TextView>(R.id.text_year)
        val textWins = itemView.findViewById<TextView>(R.id.text_wins)
        val textLosses = itemView.findViewById<TextView>(R.id.text_losses)

        init {
            itemView.setOnClickListener {
                showDrawerWithSeasonData(season)
            }
        }

        var season: Season? = null
            set(value) {
                field = value
                textYear.text = getString(R.string.item_year, season?.year ?: 1980)
                textWins.text = getString(R.string.item_wins, season?.wins ?: 81)
                textLosses.text = getString(R.string.item_losses, season?.losses ?: 81)
            }
    }

    inner class SeasonAdapter(context: Context) : RecyclerView.Adapter<SeasonViewHolder>() {

        private val inflater = LayoutInflater.from(context)
        private val seasons = Season.generateSeasons()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
            return SeasonViewHolder(
                inflater.inflate(R.layout.item_season, parent, false)
            )
        }

        override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
            holder.season = seasons[position]
        }

        override fun getItemCount(): Int {
            return seasons.size
        }
    }
}