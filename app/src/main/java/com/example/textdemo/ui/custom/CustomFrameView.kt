package com.example.textdemo.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.textdemo.ui.theme.TextDemoTheme

@Composable
fun FrameView() {
    var width by remember {
        mutableStateOf(100f)
    }
    var height by remember {
        mutableStateOf(100f)
    }

    val wRange = 100f..width
    val hRange = 100f..height

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.2f)
            .background(Color.Gray),
            contentAlignment = Alignment.Center){
            Text("1")
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding()
            .background(Color.White)
            .onSizeChanged {
                width = it.width.toFloat()
                height = it.height.toFloat()
            },
            contentAlignment = Alignment.Center
        ) {
            Text("2")
            Box(modifier = Modifier
                .width(width.dp)
                .height(height.dp)
                .background(Color.Blue))
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding()
            .weight(0.3f)
            .background(Color.Gray),
            contentAlignment = Alignment.Center){
            Text("3")
            Column {
                Text("Width: $width")
                Slider(value = width, onValueChange = { width = it }, valueRange = wRange)
                Text("Height: $height")
                Slider(value = height, onValueChange = { height = it }, valueRange = hRange)
            }
        }
    }
}


@Preview
@Composable
fun FrameViewPreview() {
    TextDemoTheme {
        FrameView()
    }
}