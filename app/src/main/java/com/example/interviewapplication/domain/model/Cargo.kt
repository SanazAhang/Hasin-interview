package com.example.interviewapplication.domain.model

data class Cargo(
    val id: Int,
    val origin: String,
    val destination: String,
    val weight: String,
    val price: String,
    val isSelected: Boolean = false
)
