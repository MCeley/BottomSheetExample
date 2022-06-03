package com.michaelceley.examples.bottomsheet

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.michaelceley.examples.bottomsheet.model.Season

class StatsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "StatsBottomSheetDialogFragment"
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        requireActivity().finish()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        requireActivity().finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seasons = Season.seasons

        var wins = 0
        var losses = 0
        var firstYear = seasons[0].year
        var lastYear = seasons[0].year
        var bestSeason = seasons[0]
        var worstSeason = seasons[0]

        seasons.forEach {
            wins += it.wins
            losses += it.losses

            if(firstYear > it.year) firstYear = it.year
            else if(lastYear < it.year) lastYear = it.year

            if(it.winRate > bestSeason.winRate) bestSeason = it
            else if(it.winRate < worstSeason.winRate) worstSeason = it
        }
        val games = wins + losses

        view.findViewById<TextView>(R.id.text_year_range).text =
            getString(R.string.year_range, firstYear, lastYear)

        view.findViewById<TextView>(R.id.text_average_games).text =
            getString(R.string.average_games_played, games.toFloat() / seasons.size)

        view.findViewById<TextView>(R.id.text_average_wins).text =
            getString(R.string.average_games_won, wins.toFloat() / seasons.size)

        view.findViewById<TextView>(R.id.text_average_losses).text =
            getString(R.string.average_games_lost, losses.toFloat() / seasons.size)

        view.findViewById<TextView>(R.id.text_best_year).text =
            getString(R.string.best_year, bestSeason.year, bestSeason.wins, bestSeason.losses)

        view.findViewById<TextView>(R.id.text_worst_year).text =
            getString(R.string.worst_year, worstSeason.year, worstSeason.wins, worstSeason.losses)
    }
}