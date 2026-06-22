package com.example.stipukha.ui.navigation

import android.annotation.SuppressLint
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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.navigation3.runtime.NavKey
import com.example.stipukha.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarNavigation(backStack: SnapshotStateList<NavKey>) {
    var selectedIndex by rememberSaveable() { mutableIntStateOf(1) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    var sumMoney by remember { mutableStateOf("0") }
    val tertiaryColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)

    val colorProducer = ColorProducer { tertiaryColor }
    LaunchedEffect(Unit) {
        scaffoldState.bottomSheetState.expand()
    }

    val currentDate = LocalDate.now()

    var selectedBoolean by remember { mutableStateOf(0) }


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = screenHeight,
        sheetContainerColor = MaterialTheme.colorScheme.primary,
        sheetContent = {
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
                                CardDate(
                                    R.string.week_1,
                                    selectedBoolean = selectedBoolean == 0,
                                    modifier = Modifier
                                        .clickable{
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
                                        .clickable{
                                            selectedBoolean = 1
                                        }
                                        .weight(0.5f),
                                    currentDate.plusMonths(1)

                                )

                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Card(
                                modifier = Modifier
                                    .clickable {

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
        }
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.primary,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = when (selectedIndex) {
                                1 -> stringResource(R.string.main)
                                2 -> stringResource(R.string.statistics)
                                else -> stringResource(R.string.capital)
                            }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    actions = {
                        if (selectedIndex == 1) {
                            IconButton(
                                onClick = {
                                    backStack.add(ScreenAdd)
                                    selectedIndex = 3
                                },
                                colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.settings),
                                    null
                                )
                            }
                        } else {


                        }

                    },

                    )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                ) {
                    NavigationBarItem(
                        selected = selectedIndex == 1,
                        onClick = {
                            selectedIndex = 1
                            backStack.add(ScreenMain)
                        },
                        icon = { Icon(painter = painterResource(R.drawable.home), null) },
                        label = { Text(stringResource(R.string.main)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.tertiary,
                            selectedTextColor = MaterialTheme.colorScheme.tertiary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    NavigationBarItem(
                        selected = selectedIndex == 2,
                        onClick = {
                            selectedIndex = 2
                            backStack.add(ScreenStats)

                        },
                        icon = { Icon(painter = painterResource(R.drawable.chart_bar), null) },
                        label = { Text(stringResource(R.string.statistics)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.tertiary,
                            selectedTextColor = MaterialTheme.colorScheme.tertiary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.primary
                        )
                    )

                }
            },
            content = { paddingValues ->
                NavDisplayNavigation(backStack, paddingValues)
            }
        )
    }
}

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
                val mouthInt = when (dateMouthOfWeek.monthValue) {
                    1 -> "Янв"
                    2 -> "Фев"
                    3 -> "Мар"
                    4 -> "Апр"
                    5 -> "Мая"
                    6 -> "Июн"
                    7 -> "Июл"
                    8 -> "Авг"
                    9 -> "Сен"
                    10 -> "Окт"
                    11 -> "Ноя"
                    12 -> "Дек"
                    else -> {}
                }
                Text(
                    "До ${dateMouthOfWeek.dayOfMonth} ${mouthInt}"


                )
            }
        }
}