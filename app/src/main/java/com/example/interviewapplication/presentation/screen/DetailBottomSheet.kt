package com.example.interviewapplication.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.interviewapplication.R
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.ui.theme.InterviewApplicationTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargoDetailBottomSheet(
    cargo: Cargo,
    onVerifyOtpButtonClicked: (cargoId:Int) -> Unit = {},
    onDismiss: () -> Unit = {},
    selectedCargoId:Int?
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White,
        modifier = Modifier,
        properties = ModalBottomSheetProperties(
            securePolicy = SecureFlagPolicy.SecureOff,
            shouldDismissOnBackPress = true, isFocusable = false
        ),
        content = {
            BottomSheet(
                cargo = cargo,
                buttonConfirmClick = { onVerifyOtpButtonClicked(cargo.id ) },
                onDismiss = onDismiss,
                selectedCargoId = selectedCargoId
            )
        }
    )
}

@Composable
fun BottomSheet(
    cargo: Cargo,
    onDismiss: () -> Unit,
    buttonConfirmClick: () -> Unit,
    selectedCargoId:Int?
) {
    Log.d("TAG", "BottomSheet: $cargo")
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.cargo_detail),
                    fontSize = 18.sp
                )
                Icon(
                    Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.dp)
                        .clickable {
                            onDismiss()
                        },
                )
            }
            HorizontalDivider()

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.origin),
                        fontSize = 14.sp
                    )
                    Text(
                        text = cargo.origin,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                HorizontalDivider()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.destination),
                        fontSize = 14.sp
                    )
                    Text(
                        text = cargo.destination,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                HorizontalDivider()

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.weight),
                        fontSize = 14.sp,
                    )
                    Text(
                        text = cargo.weight,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                HorizontalDivider()

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.cargo),
                        fontSize = 14.sp
                    )
                    Text(
                        text = cargo.type,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                HorizontalDivider()

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.packaging),
                        fontSize = 14.sp
                    )
                    Text(
                        text = cargo.packagingType,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                HorizontalDivider()

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.download_date),
                        fontSize = 14.sp
                    )
                    Text(
                        text = cargo.downloadDate,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                if (selectedCargoId == null)
                Button(
                    onClick = buttonConfirmClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)), // نارنجی
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(56.dp)
                        ,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "با ${cargo.price} بار را بر میدارم", fontSize = 18.sp,
                        fontWeight = FontWeight.Bold, color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheet() {
    InterviewApplicationTheme {
        BottomSheet(
            Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون", "یخچال", "کارتن", "۱۴۰۳/۲/۱۲"),
            onDismiss = {},
            buttonConfirmClick = {},
            selectedCargoId = null)
    }

}


