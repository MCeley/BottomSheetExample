package com.michaelceley.examples.bottomsheet.model

import kotlin.random.Random

data class Season(val year: Int, val wins: Int, val losses: Int) {
    companion object {
        val seasons by lazy { generateSeasons() }

        private fun generateSeasons() : MutableList<Season> {
            val seasons = mutableListOf<Season>()
            for (i in 1980..2010) {
                val games = Random.nextInt(100, 162)
                val wins = Random.nextInt(games)
                seasons.add(Season(i, wins, games-wins))
            }
            return seasons
        }
    }
}
