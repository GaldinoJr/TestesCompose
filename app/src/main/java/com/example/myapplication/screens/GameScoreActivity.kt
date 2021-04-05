package com.example.myapplication.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.setContent
import com.example.myapplication.lightThemeColors

class GameScoreActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ScoreView(
                    Score("Corinthians", 1, "Santos", 0)
                )
            }
        }
    }

    @Composable
    fun ScoreView(score: Score) {
        val homeScore = remember { mutableStateOf(score.homeScore) }
        val visitorScore = remember { mutableStateOf(score.visitorScore) }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .weight(1.0f)
                    .fillMaxWidth(),  horizontalArrangement = Arrangement.Center) {
                    TeamScore(
                        team = score.homeTeam,
                        score = homeScore.value,
                        onUpdate = { newScore ->
                            homeScore.value = newScore
                        }
                    )
                }
                Text(
                    text = "x",
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.Red
                    )
                )
                Row(modifier = Modifier
                    .weight(1.0f)
                    .fillMaxWidth(),  horizontalArrangement = Arrangement.Center) {
                    TeamScore(
                        team = score.visitorTeam,
                        score = visitorScore.value,
                        onUpdate = { newScore ->
                            visitorScore.value = newScore
                        }
                    )
                }
            }
            OutlinedButton(

                onClick = {
                    homeScore.value = 0
                    visitorScore.value = 0
                }
            ) {
                Text("Reset")
            }
        }
    }


    @Composable
    fun TeamScore(
        team: String,
        score: Int,
        onUpdate: (Int) -> Unit
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = team,
                style = TextStyle(
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    onUpdate(score + 1)
                }
            ) {
                Text(
                    text = "+",
                    modifier = Modifier.padding(16.dp)
                )
            }
            Text(
                text = score.toString(),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                    onUpdate((score - 1).coerceAtLeast(0))
                }
            ) {
                Text(
                    text = "-",
                    modifier = Modifier.padding(16.dp)
                )
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MaterialTheme(colors = lightThemeColors) {
            ScoreView(
                Score("Corinthians", 1, "Santos", 0)
            )
        }
    }
}