@file:Suppress("UNREACHABLE_CODE")

package com.example.interviewapplication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interviewapplication.R
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.ui.theme.InterviewApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargoListScreen(
    cargo: List<Cargo>,
    onCargoClick: (Cargo) -> Unit,
    onCancel: (cargoId: Int) -> Unit,
    selectedCargo: Int?
) {
    var selectedCargoId by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        modifier = Modifier.background(Color.Cyan),
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp) // Optional: for padding on the sides
                    ) {
                        Text(
                            text = stringResource(R.string.cargoes),
                            modifier = Modifier.align(Alignment.Center) // This centers the text
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Help icon */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.messagequestion),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Go to next */ }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(padding)
        ) {
            items(cargo) { cargo ->
                CargoCard(
                    cargo = cargo,
                    isSelected = selectedCargoId == cargo.id,
                    onClick = {
                        selectedCargoId = cargo.id
                        onCargoClick(cargo)
                    },
                    onCancel = onCancel,
                    selectedCargo = selectedCargo
                )
            }
        }
    }
}

@Composable
fun CargoCard(
    cargo: Cargo,
    isSelected: Boolean,
    onClick: () -> Unit,
    onCancel: (cargoId: Int) -> Unit,
    selectedCargo: Int?
) {
    val backgroundColor = when {
        isSelected -> Color(0xFFE0F7FA)
        else -> Color.White
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Card(
            modifier = Modifier
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp)
        )
        {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(backgroundColor)
                    .clickable { onClick() }
                    .padding(16.dp)

            ) {

                if (cargo.isAccepted) {
                    Card(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = " بار ${cargo.origin} به ${cargo.destination} انتخاب شده است ",
                                fontSize = 12.sp, fontWeight = FontWeight.Bold
                            )
                            Button(
                                onClick = { onCancel(cargo.id) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // نارنجی
                                modifier = Modifier
                                    .size(width = 130.dp, height = 56.dp)
                                    .padding(8.dp),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.cancel_cargo),
                                    fontSize = 15.sp,
                                    color = Color.Red
                                )
                            }
                        }
                    }
                    Spacer(Modifier.size(8.dp))

                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.circle),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )

                        Text(
                            text = "${cargo.origin} (${cargo.origin})",
                            fontSize = 14.sp
                        )
                    }
                    if (!cargo.isAccepted && selectedCargo != null && cargo.id != selectedCargo)
                        Icon(
                            painter = painterResource(id = R.drawable.lock),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = Color(0xFFFF9800)
                        )
                }
                VerticalDivider(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(horizontal = 8.dp)
                        .fillMaxHeight()
                        .background(Color.Gray)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.rectangle),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = "${cargo.destination} (${cargo.destination})",
                        fontSize = 14.sp
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.tons),
                            contentDescription = null
                        )
                        Text(cargo.weight)
                    }
                    Text(stringResource(R.string.toman, cargo.price), fontWeight = FontWeight.Bold)
                }
            }
        }

    }
}

@Preview
@Composable
fun Preview() {
    InterviewApplicationTheme {
        CargoListScreen(
            cargo = listOf(
                Cargo(
                    1,
                    "تهران",
                    "اصفهان",
                    "2 تن",
                    "5 میلیون",
                    "سیمان",
                    "گونی",
                    "۱۴۰۳/۲/۳۷",
                    isAccepted = true
                ),
                Cargo(2, "شیراز", "تبریز", "1 تن", "3 میلیون", "کاغذ", "گونی", "۱۴۰۳/۲/۳۱"),
                Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون", "یخچال", "کارتن", "۱۴۰۳/۲/۱۲")
            ), onCargoClick = { }, onCancel = {}, selectedCargo = null
        )
    }

}
