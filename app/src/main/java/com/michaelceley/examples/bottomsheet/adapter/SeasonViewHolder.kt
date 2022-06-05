package com.michaelceley.examples.bottomsheet.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.michaelceley.examples.bottomsheet.R
import com.michaelceley.examples.bottomsheet.model.Season

class SeasonViewHolder(itemView: View,
                       private val onSeasonSelectedListener: OnSeasonSelectedListener)
    : RecyclerView.ViewHolder(itemView) {

    private val textYear: TextView? = itemView.findViewById(R.id.text_year)
    private val textWins: TextView? = itemView.findViewById(R.id.text_wins)
    private val textLosses: TextView? = itemView.findViewById(R.id.text_losses)

    init {
        itemView.setOnClickListener {
            onSeasonSelectedListener.onSeasonSelected(season)
        }
    }

    var season: Season? = null
        set(value) {
            field = value
            itemView.context.let {
                textYear?.text = it.getString(R.string.item_year, season?.year ?: 1980)
                textWins?.text = it.getString(R.string.item_wins, season?.wins ?: 81)
                textLosses?.text = it.getString(R.string.item_losses, season?.losses ?: 81)
            }
        }
}