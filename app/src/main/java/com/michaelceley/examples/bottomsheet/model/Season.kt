package com.michaelceley.examples.bottomsheet.model

import kotlin.random.Random

data class Season(val year: Int, val wins: Int, val losses: Int) {
    companion object {
        fun generateSeasons() : List<Season> {
            val seasons = mutableListOf<Season>()
            for (i in 1980..2010) {
                val wins = Random.nextInt(162)
                seasons.add(Season(i, wins, 162-wins))
            }
            return seasons
        }
    }
}
