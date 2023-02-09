package com.example.textdemo.ui.custom

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textdemo.*
import com.example.textdemo.ui.theme.TextDemoTheme
import com.example.textdemo.ui.theme.fontFamily

@Composable
fun CompareText() {
    Column {
        BasicText(text = "Hello Folks!")
        CustomText()
        MultipleStylesInText()
        ParagraphStyle()
        LongText()
        SelectableText()
        SimpleClickableText()
        AnnotatedClickableText()
        Text(text = "Hello World", fontFamily = fontFamily)
    }
}

//Custom Font Family remains
//https://developer.android.com/jetpack/compose/text
@Composable
fun CustomText() {
    val offset = Offset(10.0f, 10.0f)
    Text(
        text = "Hello World!",
        color = Color.Blue,
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.Cursive,
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black,
                offset = offset,
                blurRadius = 20f
            )
        ),
        modifier = Modifier.width(150.dp)
    )
}

@Composable
fun MultipleStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
        }
    )
}

@Composable
fun ParagraphStyle() {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("Hello\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                ) {
                    append("World\n")
                }
                append("Compose")
            }
        }
    )
}

@Composable
fun LongText() {
    Text("hello ".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun SelectableText() {
    SelectionContainer {
        Column {
            Text("This text is selectable")
            DisableSelection {
                Text("But not this one")
            }
            Text("But again, you can select this one")
        }
    }
}

@Composable
fun SimpleClickableText() {
    ClickableText(
        text = AnnotatedString("Click Me"),
        onClick = { offset ->
            Log.d("ClickableText", "$offset -th character is clicked.")
        }
    )
}

@Composable
fun AnnotatedClickableText() {
    val annotatedText = buildAnnotatedString {
        append("Click ")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(tag = "URL",
            annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue,
            fontWeight = FontWeight.Bold)
        ) {
            append("here")
        }

        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(tag = "URL", start = offset,
                end = offset)
                .firstOrNull()?.let { annotation ->
                    // If yes, we log its value
                    Log.d("Clicked URL", annotation.item)
                }
        }
    )
}