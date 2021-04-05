package com.example.myapplication.screens

import androidx.compose.Model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//https://proandroiddev.com/how-to-write-memorable-functions-5ee4e2ba976b
@Model
class Score(
    val homeTeam: String,
    homeScore: Int,
    val visitorTeam: String,
    visitorScore: Int
): ViewModel() {
    var homeScore = MutableLiveData(homeScore)
    var visitorScore = MutableLiveData(visitorScore)
}

