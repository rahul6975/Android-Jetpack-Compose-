package com.rahul.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahul.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Rahul")
//                    ClickableText()
//                    AnimateColorComponent()
                    RotatingSquareComponent()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeDemoTheme {
        Greeting("Android")
    }
}


@Composable
fun ClickableText() {
    var showPopup by remember { mutableStateOf(false) }
    Column(Modifier.clickable(onClick = { showPopup = true }), content = {
        Card(
            shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp),
            backgroundColor = Color.LightGray
        ) {
            Text(
                text = "Click to see dialog", modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif
                )
            )
        }
    })
    val onPopupDismissed = { showPopup = false }
    if (showPopup) {
        AlertDialog(
            onDismissRequest = onPopupDismissed,
            text = {
                Text("Congratulations! You just clicked the text successfully")
            },
            confirmButton = {
                Button(
                    onClick = onPopupDismissed
                ) {
                    Text(text = "Ok")
                }
            })
    }
}

@Preview
@Composable
fun ClickableTextPreview() {
    Column {
        ClickableText()
    }
}

@Composable
fun AnimateColorComponent() {
    val currentColor by remember {
        mutableStateOf(Color.Red)
    }
    val transition = updateTransition(currentColor)
    val color by transition.animateColor(
        transitionSpec = { TweenSpec<Color>(durationMillis = 2000) }
    ) { state ->
        when (state) {
            Color.Red -> Color.Green
            Color.Green -> Color.Blue
            Color.Blue -> Color.Red
            else -> Color.Red
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    ) { }
}

@Preview
@Composable
fun AnimateColorComponentPreview() {
    AnimateColorComponent()
}

@Composable
fun RotatingSquareComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            val infiniteTransition = rememberInfiniteTransition()
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween<Float>(
                        durationMillis = 3000,
                        easing = FastOutLinearInEasing,
                    ),
                )
            )
            Canvas(modifier = Modifier.size(200.dp)) {
                rotate(rotation) {
                    drawRect(color = Color(255, 138, 128))
                }
            }
        })
}

@Preview
@Composable
fun RotatingSquareComponentPreview() {
    RotatingSquareComponent()
}