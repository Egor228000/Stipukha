package com.example.stipukha.ui.feature_main.components

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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicAlertDialogCustom() {
    val tertiaryColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)
    var sumMoney by remember { mutableStateOf("0") }

    val colorProducer = ColorProducer { tertiaryColor }
    val currentDate = LocalDate.now()
    var selectedBoolean by remember { mutableStateOf(0) }

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var formattedDate by remember {
        mutableStateOf(selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
    }
    var showCustomPicker by remember { mutableStateOf(false) }

    BasicAlertDialog(
        onDismissRequest = { true },
        modifier = Modifier.fillMaxSize(),
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        content = {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier

                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)

                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                stringResource(R.string.onboarding_title),
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.W700,

                                )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                stringResource(R.string.onboarding_subtitle),
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W400,
                                textAlign = TextAlign.Center

                            )
                        }
                        Spacer(modifier = Modifier.height(36.dp))

                        Text(
                            stringResource(R.string.budget_question),
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,

                            )
                        Spacer(modifier = Modifier.height(8.dp))


                        TextField(
                            sumMoney,
                            { sumMoney = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),

                            modifier = Modifier
                                .clip(RoundedCornerShape(20))
                                .height(100.dp),
                            textStyle = TextStyle(
                                fontSize = 64.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
                                textAlign = TextAlign.End
                            ),
                            leadingIcon = {
                                Icon(
                                    painterResource(
                                        R.drawable.ruble_sign_svgrepo_com,

                                        ),
                                    tint = colorProducer,
                                    null,
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .size(50.dp)
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

                        Spacer(modifier = Modifier.height(36.dp))

                        Text(
                            stringResource(R.string.deadline_question),
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,

                            )
                        Spacer(modifier = Modifier.height(8.dp))

                        Column(

                        ) {
                            Row() {
                                if (formattedDate.toString() == currentDate.format(
                                        DateTimeFormatter.ofPattern(
                                            "dd.MM.yyyy"
                                        )
                                    )
                                ) {


                                    CardDate(
                                        R.string.week_1,
                                        selectedBoolean = selectedBoolean == 0,
                                        modifier = Modifier
                                            .clickable {
                                                selectedBoolean = 0
                                            }
                                            .weight(0.5f),
                                        currentDate.plusWeeks(1),

                                        )

                                    Spacer(modifier = Modifier.width(16.dp))
                                    CardDate(
                                        R.string.month_1,
                                        selectedBoolean = selectedBoolean == 1,
                                        modifier = Modifier
                                            .clickable {
                                                selectedBoolean = 1
                                            }
                                            .weight(0.5f),
                                        currentDate.plusMonths(1)

                                    )
                                } else {
                                    Text(
                                        "До ${formattedDate}",
                                        fontWeight = FontWeight.W500,
                                        fontSize = 25.sp,
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Card(
                                modifier = Modifier
                                    .clickable {
                                        showCustomPicker = true
                                    }
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.tertiary
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                ) {
                                    Icon(
                                        painterResource(
                                            R.drawable.calendar_month
                                        ),
                                        null

                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        stringResource(R.string.pick_other_date),
                                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W500,

                                        )
                                    Spacer(modifier = Modifier.width(40.dp))

                                    Icon(
                                        painterResource(
                                            R.drawable.arrow_narrow_left
                                        ),
                                        modifier = Modifier
                                            .scale(scaleY = 1f, scaleX = -1f),
                                        contentDescription = null

                                    )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(64.dp))

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .height(70.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    stringResource(R.string.lets_go),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.W500,

                                    )
                                Spacer(modifier = Modifier.width(16.dp))

                                Icon(
                                    painterResource(
                                        R.drawable.arrow_narrow_left
                                    ),
                                    modifier = Modifier
                                        .size(32.dp)
                                        .scale(
                                            scaleY = 1f,
                                            scaleX = -1f
                                        ),
                                    contentDescription = null
                                )
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
                        formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        showCustomPicker = false
                    }
                )
            }
        }
    )
}