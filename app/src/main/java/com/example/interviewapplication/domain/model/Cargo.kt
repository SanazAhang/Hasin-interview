package com.example.interviewapplication.domain.model

data class Cargo(
    val id: Int = 0,
    val origin: String="",
    val destination: String="",
    val weight: String="",
    val price: String="",
    val type:String="",
    val packagingType:String="",
    val downloadDate:String="",
    val isSelected: Boolean = false,
    val isAccepted: Boolean = false
)
