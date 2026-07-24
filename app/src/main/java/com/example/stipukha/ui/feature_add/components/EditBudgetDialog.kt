package com.example.stipukha.ui.feature_add.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.stipukha.R
import com.example.stipukha.ui.feature_main.components.CustomDatePickerDialog
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBudgetDialog(
    initialAmountCents: Long,
    currentEndDateTimestamp: Long,
    onDismiss: () -> Unit,
    onConfirm: (Long, Long) -> Unit
) {
    var sumMoney by remember { mutableStateOf((initialAmountCents / 100).toString()) }
    val currentDate = java.time.LocalDate.now()
    
    val initialDate = if (currentEndDateTimestamp > 0) {
        Instant.ofEpochMilli(currentEndDateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate()
    } else {
        currentDate.plusMonths(1)
    }

    var selectedDate by remember { mutableStateOf(initialDate) }
    var showCustomPicker by remember { mutableStateOf(false) }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxSize(),
        properties = DialogProperties(usePlatformDefaultWidth = false),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp).fillMaxSize()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(R.string.edit_budget_title),
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = stringResource(R.string.total_amount_label),
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = sumMoney,
                            onValueChange = { sumMoney = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.clip(RoundedCornerShape(20)).height(80.dp).fillMaxWidth(),
                            textStyle = TextStyle(
                                fontSize = 48.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.End
                            ),
                            leadingIcon = {
                                Icon(
                                    painterResource(R.drawable.ruble_sign_svgrepo_com),
                                    tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
                                    contentDescription = null,
                                    modifier = Modifier.padding(start = 16.dp).size(40.dp)
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.primary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = stringResource(R.string.end_date_label),
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier.clickable { showCustomPicker = true }.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(painterResource(R.drawable.calendar_month), null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = selectedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W500,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    painterResource(R.drawable.pencil),
                                    modifier = Modifier.size(20.dp),
                                    contentDescription = null
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(48.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Button(
                                onClick = onDismiss,
                                modifier = Modifier.height(60.dp).weight(1f),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(stringResource(R.string.cancel), color = MaterialTheme.colorScheme.tertiary)
                            }
                            Button(
                                onClick = {
                                    val amount = sumMoney.toLongOrNull() ?: 0L
                                    val timestamp = selectedDate.atTime(LocalTime.MAX)
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()
                                        .toEpochMilli()
                                    onConfirm(amount * 100, timestamp)
                                },
                                modifier = Modifier.height(60.dp).weight(1f),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(stringResource(R.string.save), color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                }
            }
            if (showCustomPicker) {
                CustomDatePickerDialog(
                    onDismiss = { showCustomPicker = false },
                    onDateSelected = { date ->
                        selectedDate = date
                        showCustomPicker = false
                    }
                )
            }
        }
    )
}
