package com.michaelceley.examples.bottomsheet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.michaelceley.examples.bottomsheet.model.Season

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Celey Sluggers"

        val recyclerView = findViewById<RecyclerView>(R.id.rv_seasons)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = SeasonAdapter(this)
    }

    inner class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textYear = itemView.findViewById<TextView>(R.id.text_year)
        val textWins = itemView.findViewById<TextView>(R.id.text_wins)
        val textLosses = itemView.findViewById<TextView>(R.id.text_losses)

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