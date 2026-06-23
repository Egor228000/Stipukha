package com.example.stipukha.ui.feature_main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.stipukha.R
import com.example.stipukha.ui.navigation.CardDate
import com.example.stipukha.ui.navigation.CustomDatePickerDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun MainScreen() {

    var sumMoney by remember { mutableStateOf("0") }
    val tertiaryColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)

    val colorProducer = ColorProducer { tertiaryColor }
    val currentDate = LocalDate.now()
    var selectedBoolean by remember { mutableStateOf(0) }

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var formattedDate by remember {
        mutableStateOf(selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
    }
    var showCustomPicker by remember { mutableStateOf(false) }
    var modalSheet = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        modalSheet.partialExpand()
    }
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
        })



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val categoryMap = remember {
            mapOf(
                "Еда" to R.drawable.tools_kitchen_2,
                "Транспорт" to R.drawable.car,
                "Развлечение" to R.drawable.device_gamepad_2,
                "Здоровье" to R.drawable.dental,
                "Другое" to R.drawable.sketching
            )
        }
        val keys = remember {
            listOf(
                "1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                ".", "0", "backspace"
            )
        }
        var selectedCategory by remember { mutableStateOf("Еда") }

        BalanceCard(
            350,
            12000,
            24
        )

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            categoryMap.forEach { (categoryName, iconRes) ->
                CategoryCard(
                    textButton = categoryName,
                    iconButton = iconRes,
                    isSelected = categoryName == selectedCategory,
                    onClick = { selectedCategory = categoryName }
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        var sum by remember { mutableStateOf("0") }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.defaultMinSize(minHeight = 60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sum.take(21),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(keys) { key ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .clickable {
                            if (key == "backspace") {
                                if (sum.isNotEmpty()) {
                                    sum = sum.dropLast(1)
                                    if (sum.isEmpty()) sum = "0"
                                }
                            } else {
                                if (sum.length > 20) {


                                } else {
                                    if (sum == "0" && key != ".") {
                                        sum = key
                                    } else {
                                        sum += key
                                    }
                                }
                            }
                        }
                ) {
                    if (key == "backspace") {
                        Icon(
                            painter = painterResource(R.drawable.backspace),
                            contentDescription = "Удалить",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    } else {
                        Text(
                            text = key,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            ButtonIntent(
                R.string.replenish,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.48f)
                    .align(Alignment.BottomStart),
                colorsButton = MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.tertiary
            )
            ButtonIntent(
                R.string.spent,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.48f)
                    .align(Alignment.BottomEnd),
                colorsButton = MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.onPrimary

            )
        }


    }
}


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

@Composable
fun BalanceCard(
    balance: Int,
    balanceAll: Int,
    days: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Доступно на сегодня",
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                fontWeight = FontWeight.W500,
                fontSize = 18.sp

            )
            Spacer(modifier = Modifier.height(8.dp))
            // Счет
            Text(
                balance.toString(),
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 60.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Всего ${balanceAll} Р * Осталось: ${days} дня",
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    textButton: String,
    iconButton: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val containerColor =
        if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
    val contentColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary

    Button(
        onClick = onClick,
        colors = buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconButton),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = textButton,
                color = contentColor,
                fontSize = 16.sp
            )
        }
    }
}