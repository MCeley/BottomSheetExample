package com.michaelceley.examples.bottomsheet.model

enum class SortMode(val text: String) {
    SORT_YEAR_ASC("Year Ascending"),
    SORT_YEAR_DSC("Year Descending"),
    SORT_WINS_ASC("Wins Ascending"),
    SORT_WINS_DSC("Wins Descending"),
    SORT_LOSSES_ASC("Losses Ascending"),
    SORT_LOSSES_DSC("Losses Descending");

    override fun toString(): String {
        return text
    }
}