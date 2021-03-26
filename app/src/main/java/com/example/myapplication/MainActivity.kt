package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

// custom background
val lightThemeColors = lightColors(
    primary = Color(0xFF0000FF),
    primaryVariant = Color(0xFF9C684B),
    secondary = Color(0xFF03DAC5),
    secondaryVariant = Color(0xFF0AC9F0),
    background = Color.White,
    surface = Color.Green, // cor do botao
    error = Color(0xFFB00020),
    onPrimary = Color.White, // cor do texto
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

// todo alinhar items na horizontal
@Composable
fun Greeting(name: String) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            "nome" +
                    "" + name
        )
        Text("Col 2")
        Text("Col 3")
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
//                onUpdate(max(score - 1, 0))
            }
        ) {
            Text(
                text = "-",
                modifier = Modifier.padding(16.dp)
            )
        }

    }
}

// Opção 2
data class Score(
    val homeTeam: String,
    var homeScore: Int,
    val visitorTeam: String,
    var visitorScore: Int
)

@Composable
fun ScoreView(score: Score) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1.0f).fillMaxWidth(),  horizontalArrangement = Arrangement.Center) {
                TeamScore(
                    team = score.homeTeam,
                    score = score.homeScore,
                    onUpdate = { newScore ->
                        score.homeScore = newScore
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
            Row(modifier = Modifier.weight(1.0f).fillMaxWidth(),  horizontalArrangement = Arrangement.Center) {
                TeamScore(
                    team = score.visitorTeam,
                    score = score.visitorScore,
                    onUpdate = { newScore ->
                        score.visitorScore = newScore
                    }
                )
            }
        }
        OutlinedButton(

            onClick = {
                score.homeScore = 0
                score.visitorScore = 0
            }
        ) {
            Text("Reset")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme(colors = lightThemeColors) {
        ScoreView(
            Score("Corinthians", 0, "Santos", 0)
        )
    }
}