package com.michaelceley.examples.bottomsheet.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaelceley.examples.bottomsheet.R
import com.michaelceley.examples.bottomsheet.model.Season
import com.michaelceley.examples.bottomsheet.model.SortMode


class SeasonAdapter(context: Context,
                    private val onSeasonSelectedListener: OnSeasonSelectedListener)
    : RecyclerView.Adapter<SeasonViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val seasons = Season.seasons

    @SuppressLint("NotifyDataSetChanged")
    fun applySort(sortMode: SortMode) {
        when(sortMode) {
            SortMode.SORT_YEAR_ASC -> seasons.sortBy { it.year }
            SortMode.SORT_YEAR_DSC -> seasons.sortByDescending { it.year }
            SortMode.SORT_WINS_ASC -> seasons.sortBy { it.wins }
            SortMode.SORT_WINS_DSC -> seasons.sortByDescending { it.wins }
            SortMode.SORT_LOSSES_ASC -> seasons.sortBy { it.losses }
            SortMode.SORT_LOSSES_DSC -> seasons.sortByDescending { it.losses }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(
            inflater.inflate(R.layout.item_season, parent, false),
            onSeasonSelectedListener
        )
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.season = seasons[position]
    }

    override fun getItemCount(): Int {
        return seasons.size
    }
}