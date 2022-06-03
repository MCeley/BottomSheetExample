package com.michaelceley.examples.bottomsheet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.michaelceley.examples.bottomsheet.model.Season

class StatsViewModel(application: Application) : AndroidViewModel(application) {

    val firstYear = MutableLiveData<Int>()
    val lastYear = MutableLiveData<Int>()
    val averageGamesPlayed = MutableLiveData<Float>()
    val averageGamesWon = MutableLiveData<Float>()
    val averageGamesLost = MutableLiveData<Float>()
    val bestSeason = MutableLiveData<Season>()
    val worstSeason = MutableLiveData<Season>()

    init {
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

        this.firstYear.value = firstYear
        this.lastYear.value = lastYear
        averageGamesPlayed.value = games.toFloat() / seasons.size
        averageGamesWon.value = wins.toFloat() / seasons.size
        averageGamesLost.value = losses.toFloat() / seasons.size
        this.bestSeason.value = bestSeason
        this.worstSeason.value = worstSeason
    }
}