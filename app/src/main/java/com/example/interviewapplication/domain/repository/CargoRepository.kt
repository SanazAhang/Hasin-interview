package com.example.interviewapplication.domain.repository

import com.example.interviewapplication.domain.model.Cargo

interface CargoRepository {
    suspend fun getAllCargos(): List<Cargo>
}