package com.rahul.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp

class BackPressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = remember { AppState() }
            BackPressApp(appState)
        }
    }
}

@Composable
fun BackPressApp(appState: AppState) {
    when (appState.currentScreen) {
        CurrentScreen.SCREEN1 -> Screen1(appState)
        CurrentScreen.SCREEN2 -> Screen2(appState)
        CurrentScreen.SCREEN3 -> Screen3(appState)
    }
}

@Composable
fun Screen1(appState: AppState) {
    val activity = (LocalLifecycleOwner.current as ComponentActivity)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleComponent(title = "This is Screen 1")
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            onClick = {
                appState.currentScreen = CurrentScreen.SCREEN2
            }) {
            TitleComponent(title = "Go To Screen 2")
        }
        TitleComponent(title = "Press back to exit this activity")
    }
    BackButtonHandler {
        activity.finish()
    }
}

@Composable
fun Screen2(appState: AppState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleComponent(title = "This is Screen 2")
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            onClick = {
                appState.currentScreen = CurrentScreen.SCREEN3
            }) {
            TitleComponent(title = "Go To Screen 3")
        }
        TitleComponent(title = "Press back to go to Screen 1")
    }
    BackButtonHandler {
        appState.currentScreen = CurrentScreen.SCREEN1
    }
}

@Composable
fun Screen3(appState: AppState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleComponent(title = "This is Screen 3")
        TitleComponent(title = "You can only go back from here. Press back to go to Screen 2.")
    }
    BackButtonHandler {
        appState.currentScreen = CurrentScreen.SCREEN2
    }
}

class AppState {
    var currentScreen by mutableStateOf(CurrentScreen.SCREEN1)
}

enum class CurrentScreen {
    SCREEN1,
    SCREEN2,
    SCREEN3
}