package com.example.interviewapplication.data.source.mock

import com.example.interviewapplication.domain.model.Cargo


interface CargoDataSource {
    suspend fun getCargos(): List<Cargo>
}
