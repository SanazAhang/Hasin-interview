package com.example.interviewapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.presentation.screen.CargoDetailBottomSheet
import com.example.interviewapplication.presentation.screen.CargoListScreen
import com.example.interviewapplication.presentation.viewmodel.CargoViewModel
import com.example.interviewapplication.presentation.viewmodel.DetailBottomSheet
import com.example.interviewapplication.ui.theme.InterviewApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CargoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val cargoList = viewModel.cargoList.value
            val detailBottomSheet = viewModel.detail
            InterviewApplicationTheme {

                CargoListScreen(
                    cargoList
                ) {
                    viewModel.onItemClick(it)
                }

                if (detailBottomSheet.value.isVisible){
                    Log.d("TAG", "onCreate: ${detailBottomSheet.value.selectedCargo}")
                    CargoDetailBottomSheet(
                        onVerifyOtpButtonClicked = {
                        viewModel.acceptCargo()
                    }, onDismiss = {
                        viewModel.dismissBottomSheet()
                    },
                        cargo = detailBottomSheet.value.selectedCargo,)
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