package com.example.textdemo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.textdemo.R


@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
val MontserratFont = GoogleFont(name = "Lobster Two")

@OptIn(ExperimentalTextApi::class)
val fontFamily = FontFamily(
    Font(googleFont = MontserratFont, fontProvider = provider)
)

//@OptIn(ExperimentalTextApi::class)
//val AppFontTypography = Typography(
//    defaultFontFamily = getGoogleFontFamily(
//        name = "Qwitcher Grypen",
//        weights = listOf(
//            FontWeight.Normal,
//            FontWeight.Bold,
//            FontWeight.ExtraLight,
//            FontWeight.SemiBold
//        )
//    )
//)

//@OptIn(ExperimentalTextApi::class)
//private fun getGoogleFontFamily(
//    name: String,
//    provider: GoogleFont.Provider = provider,
//    weights: List<FontWeight>
//): FontFamily {
//    return FontFamily(
//        weights.map {
//            Font(GoogleFont(name), provider, it)
//        }
//    )
//}

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)