package com.example.stipukha.ui.feature_main.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardDate(
    textDate: Int,
    selectedBoolean: Boolean,
    modifier: Modifier = Modifier,
    dateMouthOfWeek: LocalDate,
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            if (selectedBoolean) MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.tertiary
        )

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                stringResource(textDate),
                fontSize = 24.sp,
                fontWeight = FontWeight.W700

            )
            val monthResId = when (dateMouthOfWeek.monthValue) {
                1 -> R.string.jan
                2 -> R.string.feb
                3 -> R.string.mar
                4 -> R.string.apr
                5 -> R.string.may
                6 -> R.string.jun
                7 -> R.string.jul
                8 -> R.string.aug
                9 -> R.string.sep
                10 -> R.string.oct
                11 -> R.string.nov
                12 -> R.string.dec
                else -> R.string.other
            }
            Text(
                stringResource(R.string.until_date, dateMouthOfWeek.dayOfMonth, stringResource(monthResId))
            )
        }
    }
}
