package com.example.myapplication.screens

import androidx.compose.Model

@Model
class Score(
    val homeTeam: String,
    var homeScore: Int,
    val visitorTeam: String,
    var visitorScore: Int
)