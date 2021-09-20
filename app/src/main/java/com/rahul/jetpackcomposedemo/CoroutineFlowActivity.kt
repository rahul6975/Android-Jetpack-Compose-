package com.rahul.jetpackcomposedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoroutineFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is an extension function of Activity that sets the @Co
        setContent {
            FlowComponent(countdownFlow())
        }
    }
}

@Composable
fun FlowComponent(flow: Flow<Int>) {
    val countDownValue by flow.collectAsState(initial = 10)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (countDownValue) {
            in 1..10 -> {
                CountdownText("Countdown: $countDownValue")
            }
            else -> {
                CountdownText("HAPPY NEW YEAR!!!!", Color.Magenta)
            }
        }
    }

}

@Composable
fun CountdownText(text: String, color: Color = Color.Green) {
    Text(
        text = text,
        color = color,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    )
}

fun countdownFlow() = flow<Int> {
    for (i in 9 downTo 0) {
        delay(1000L)
        emit(i)
    }
}