package com.example.interviewapplication.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.domain.repository.CargoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CargoListViewModel @Inject constructor(
    private val repository: CargoRepository
) : ViewModel() {

    private val _cargoList = mutableStateOf<List<Cargo>>(emptyList())
    val cargoList: State<List<Cargo>> = _cargoList

    init {
        viewModelScope.launch {
            _cargoList.value = repository.getAllCargos()
        }
    }
}
