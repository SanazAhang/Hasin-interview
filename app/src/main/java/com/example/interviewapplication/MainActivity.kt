package com.example.interviewapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.interviewapplication.presentation.screen.CargoDetailBottomSheet
import com.example.interviewapplication.presentation.screen.CargoListScreen
import com.example.interviewapplication.presentation.viewmodel.CargoViewModel
import com.example.interviewapplication.ui.theme.InterviewApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CargoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val cargoList = viewModel.cargoList.value
            val detailBottomSheet = viewModel.detail
            val selectedCargoId = viewModel.selectedCargoId
            viewModel.getCargos()
            InterviewApplicationTheme {

                CargoListScreen(
                    cargo = cargoList,
                    onCargoClick = {
                        viewModel.onItemClick(it)
                    }, onCancel = { cargoId ->
                        viewModel.onCancelCargo(cargoId)
                    }, selectedCargo = selectedCargoId.value
                )

                if (detailBottomSheet.value.isVisible) {
                    Log.d("TAG", "onCreate: ${detailBottomSheet.value.selectedCargo}")
                    CargoDetailBottomSheet(
                        onVerifyOtpButtonClicked = { cargoId ->
                            viewModel.acceptCargo(cargoId)
                        }, onDismiss = {
                            viewModel.dismissBottomSheet()
                        },
                        cargo = detailBottomSheet.value.selectedCargo,
                        selectedCargoId = selectedCargoId.value
                    )
                }
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewApplicationTheme {
    }
}