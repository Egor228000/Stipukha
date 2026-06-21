package com.example.stipukha.ui.navigation

import android.annotation.SuppressLint
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

    LaunchedEffect(Unit) {
        scaffoldState.bottomSheetState.expand()
    }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = screenHeight,
        sheetContainerColor = MaterialTheme.colorScheme.primary,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
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
                                "С чего начнем?",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.W700,

                                )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "Давай настроим твой бюджетный лимит",
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W400,
                                textAlign = TextAlign.Center

                            )
                        }
                        Spacer(modifier = Modifier.height(36.dp))

                        Text(
                            "Сколько у тебя сейчас денег?",
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,

                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                        ) {
                            TextField(
                                sumMoney,
                                {sumMoney = it},
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20))
                                    .height(100.dp),
                                textStyle = TextStyle(
                                            fontSize = 64.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)
                                )

                            )
                        }
                        Spacer(modifier = Modifier.height(36.dp))

                        Text(
                            "До какой даты нужно растянуть?",
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,

                            )
                        Spacer(modifier = Modifier.height(8.dp))

                        Column(

                        ) {
                            Row() {
                                Card(
                                    modifier = Modifier
                                        .weight(0.5f)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally


                                    ) {
                                        Text(
                                            "1 неделя",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.W700
                                        )
                                        Text(
                                            "До 15 Окт"
                                        )
                                    }

                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Card(
                                    modifier = Modifier
                                        .weight(0.5f)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally

                                    ) {
                                        Text(
                                            "1 месяц",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.W700

                                        )
                                        Text(
                                            "До 15 Ноя"

                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                ) {
                                    Icon(
                                        painterResource(
                                            R.drawable.home
                                        ),
                                        null

                                    )
                                    Text(
                                        "Выбрать другую дату",
                                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W500,

                                        )
                                    Icon(
                                        painterResource(
                                            R.drawable.home
                                        ),
                                        null

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
                                    "Погнали",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.W500,

                                    )
                                Spacer(modifier = Modifier.width(16.dp))

                                Icon(
                                   painterResource(
                                       R.drawable.arrow_narrow_left
                                   ) ,
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