package com.example.interviewapplication.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.domain.repository.CargoRepository
import com.example.interviewapplication.domain.usecase.base.GetAllCargosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CargoViewModel @Inject constructor(
    private val getCargoUseCase: GetAllCargosUseCase
) : ViewModel() {

    private val _cargoList = mutableStateOf<List<Cargo>>(emptyList())
    val cargoList: State<List<Cargo>> = _cargoList
    private val _detail: MutableState<DetailBottomSheet> = mutableStateOf(DetailBottomSheet())
    val detail: State<DetailBottomSheet> = _detail
    private val _selectedCargoId: MutableState<Int?> = mutableStateOf(null)
    val selectedCargoId: State<Int?> = _selectedCargoId

    init {
        viewModelScope.launch {
            _cargoList.value = getCargoUseCase.execute(Unit)
        }
    }
    fun onItemClick(cargo: Cargo){
        _detail.value= DetailBottomSheet(true,cargo)
    }

    fun acceptCargo(cargoId:Int){

        val updatedCargos = _cargoList.value.map { cargo ->
            if (cargo.id == cargoId) {
                _selectedCargoId.value = cargoId
                cargo.copy(isAccepted = true)
            } else {
                cargo.copy(isAccepted = false)
            }
        }
        dismissBottomSheet()
        _cargoList.value = updatedCargos
    }
    fun dismissBottomSheet(){
        _detail.value= DetailBottomSheet(false)
    }

    fun onCancelCargo(cargoId: Int) {
        val updatedCargos = _cargoList.value.map { cargo ->
            if (cargo.id == cargoId) {
                _selectedCargoId.value = null
                cargo.copy(isAccepted = false)
            } else cargo
        }
        _cargoList.value = updatedCargos
    }

}

data class DetailBottomSheet(
    val isVisible: Boolean = false,
    val selectedCargo:Cargo = Cargo()
)
