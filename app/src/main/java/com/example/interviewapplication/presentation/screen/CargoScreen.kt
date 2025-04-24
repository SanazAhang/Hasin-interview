@file:Suppress("UNREACHABLE_CODE")

package com.example.interviewapplication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.ui.theme.InterviewApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargoListScreen(cargo: List<Cargo>, onCargoClick: (Cargo) -> Unit) {
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
                            text = "لیست بارها",
                            modifier = Modifier.align(Alignment.Center) // This centers the text
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Help icon */ }) {
                        Icon(Icons.Default.Info, contentDescription = null)
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
//                    isDisabled = selectedCargoId != null && selectedCargoId != cargo.id,
                    onClick = {
                        selectedCargoId = cargo.id
                        onCargoClick(cargo)
                    }
                )
            }
        }
    }
}

@Composable
fun CargoCard(
    cargo: Cargo,
    isSelected: Boolean,
//    isDisabled: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isSelected -> Color(0xFFE0F7FA)
//        isDisabled -> Color.LightGray
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
                    .clickable() { onClick() }
                    .padding(16.dp)

            ) {
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
                            Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )

                        Text(
                            text = "${cargo.origin} (${cargo.origin})",
                            fontSize = 14.sp
                        )
                    }
                    if (cargo.isAccepted == null || cargo.isAccepted == false)
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
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
                        Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = "${cargo.destination} (${cargo.destination})",
                        fontSize = 14.sp
                    )
                }

                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(Icons.Default.Lock, contentDescription = null)
                        Text(cargo.weight)
                    }
                    Text("${cargo.price} تومان", fontWeight = FontWeight.Bold)
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
                Cargo(1, "تهران", "اصفهان", "2 تن", "5 میلیون", "سیمان", "گونی", "۱۴۰۳/۲/۳۷"),
                Cargo(2, "شیراز", "تبریز", "1 تن", "3 میلیون", "کاغذ", "گونی", "۱۴۰۳/۲/۳۱"),
                Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون", "یخچال", "کارتن", "۱۴۰۳/۲/۱۲")
            ), onCargoClick = { }
        )
    }

}
