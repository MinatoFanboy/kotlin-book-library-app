package com.example.kotlinbooklibraryapp.presentation.UIcomponent

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.kotlinbooklibraryapp.R

val IconFontFamily = FontFamily(
    Font(R.font.icomoon, FontWeight.Normal),
)

@Composable
fun Icon(
    icon: String, 
    size: TextUnit = 24.sp, 
    color: Color = Color.Unspecified, 
    modifier: Modifier = Modifier
) {
    Text(
        icon, 
        fontFamily = IconFontFamily, 
        fontSize = size, 
        color = color, 
        modifier = modifier
    )
}