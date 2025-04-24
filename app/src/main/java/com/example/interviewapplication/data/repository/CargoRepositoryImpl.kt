package com.example.interviewapplication.data.repository

import com.example.interviewapplication.data.source.mock.CargoDataSource
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.domain.repository.CargoRepository
import javax.inject.Inject

class CargoRepositoryImpl @Inject constructor(
    private val dataSource: CargoDataSource
) : CargoRepository {
    override suspend fun getAllCargos(): List<Cargo> {
        return dataSource.getCargos()
    }
}
