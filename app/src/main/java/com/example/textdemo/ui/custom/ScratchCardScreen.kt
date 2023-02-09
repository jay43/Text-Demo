package com.example.textdemo.ui.custom

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.textdemo.R
import com.example.textdemo.ui.theme.TextDemoTheme

data class DraggedPath(
    val path: Path,
    val width: Float = 50f
)
@ExperimentalComposeUiApi
@Composable
fun ScratchCardScreen() {
    val overlayImage = ImageBitmap.imageResource(id = R.drawable.ic_overlay_credit)
    val baseImage = ImageBitmap.imageResource(id = R.drawable.ic_credit_card)

    var currentPathState by remember { mutableStateOf(DraggedPath(path = Path())) }
    var movedOffsetState by remember { mutableStateOf<Offset?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        IconButton(
            onClick = {
                movedOffsetState = null
                currentPathState = DraggedPath(path = Path())
            },
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Icon(
                imageVector = Icons.Default.Clear, contentDescription = "Clear",
                tint = MaterialTheme.colors.onPrimary
            )
        }

        // Scratch Card Implementation
        ScratchingCanvas(
            overlayImage = overlayImage,
            baseImage = baseImage,
            modifier = Modifier.align(Alignment.Center),
            movedOffset = movedOffsetState,
            onMovedOffset = { x, y ->
                movedOffsetState = Offset(x, y)
            },
            currentDraggedPath = currentPathState
//            currentPath = currentPathState.path,
//            currentPathThickness = currentPathState.width,
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun ScratchingCanvas(
    overlayImage: ImageBitmap,
    baseImage: ImageBitmap,
    modifier: Modifier = Modifier,
    movedOffset: Offset?,
    onMovedOffset: (Float, Float) -> Unit,
    currentDraggedPath: DraggedPath
) {
    Canvas(
        modifier = modifier
            .width(300.dp)
            .height(200.dp)
            .clipToBounds()
            .clip(RoundedCornerShape(size = 16.dp))
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        println("CurrentPath/ACTION_DOWN: (${it.x}, ${it.y})")
                        currentDraggedPath.path.moveTo(it.x, it.y)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        println("MovedOffset/ACTION_MOVE: (${it.x}, ${it.y})")
                        onMovedOffset(it.x, it.y)
                    }
                }
                true
            }
    ) {
        val canvasWidth = size.width.toInt()
        val canvasHeight = size.height.toInt()
        val imageSize = IntSize(width = canvasWidth, height = canvasHeight)

        // Overlay Image to be scratched
        drawImage(
            image = overlayImage,
            dstSize = imageSize
        )

        movedOffset?.let {
            currentDraggedPath.path.addOval(oval = Rect(it, currentDraggedPath.width))
        }

        clipPath(path = currentDraggedPath.path, clipOp = ClipOp.Intersect) {
            // Base Image after scratching
            drawImage(
                image = baseImage,
                dstSize = imageSize
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun ScratchCardScreen_Preview() {
    TextDemoTheme {
        ScratchCardScreen()
    }
}
