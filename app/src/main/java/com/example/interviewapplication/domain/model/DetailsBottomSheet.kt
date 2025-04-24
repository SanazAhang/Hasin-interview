package com.example.interviewapplication.domain.model

data class DetailBottomSheet(
    val isVisible: Boolean = false,
    val selectedCargo: Cargo = Cargo()
)