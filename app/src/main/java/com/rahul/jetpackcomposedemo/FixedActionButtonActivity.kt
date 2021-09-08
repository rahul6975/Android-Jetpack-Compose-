package com.rahul.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class FixedActionButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldWithBottomBarAndCutout()
        }
    }
}

@Composable
fun ScaffoldWithBottomBarAndCutout() {
    val fabShape = RoundedCornerShape(50)
    Scaffold(
        topBar = { TopAppBar(title = { Text("Scaffold Examples") }) },
        bottomBar = {
            BottomAppBar(cutoutShape = fabShape) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = fabShape,
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(padding)
                    .scrollable(scrollState, orientation = Orientation.Vertical)
            ) {
                repeat(100) {
                    Card(
                        backgroundColor = colors[it % colors.size],
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    )
}

@Preview("Fixed Aaction Button Example")
@Composable
fun ScaffoldWithBottomBarAndCutoutPreview() {
    ScaffoldWithBottomBarAndCutout()
}