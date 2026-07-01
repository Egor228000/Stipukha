package com.example.stipukha.ui.feature_main.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun ButtonIntent(
    textButton: Int,
    modifier: Modifier = Modifier,
    colorsButton: Color,
    colorText: Color
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorsButton),
        modifier = modifier
    ) {
        Text(
            stringResource(textButton),
            color = colorText,
            fontSize = 20.sp,
        )
    }
}