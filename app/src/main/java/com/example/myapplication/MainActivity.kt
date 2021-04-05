package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = lightThemeColors) {
                LifecycleDemo(CounterState2(1))
            }
        }
    }
}

data class CounterState2(var count: Int = 0)

//var count =  MutableLiveData(0)
//private val _message = MutableLiveData("Hi $userId")
//private val test4 = MutableLiveData(CounterState2(1))
@Composable
fun LifecycleDemo(test: CounterState2) {
//    val score = CounterState() //rememberSaveable { mutableStateOf(CounterState(1))}
    val count = remember { mutableStateOf(test.count) }


    Column {
        Button(onClick = {
            count.value++

        }) {
            Text("Click me")
        }

//        if (count.value < 3) {
            SideEffect {
                Log.d("Compose", "onactive with value: " + count.value)
            }
            DisposableEffect(Unit) {
                onDispose {
                    Log.d("Compose", "onDispose because value=" + count.value)
                }
            }

            Text(text = "You have clicked the button: " + count.value)
//        }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme(colors = lightThemeColors) {
        LifecycleDemo(CounterState2(1))
    }
}