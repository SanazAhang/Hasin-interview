package com.example.interviewapplication.domain.usecase.base

import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.domain.repository.CargoRepository
import javax.inject.Inject

class GetAllCargosUseCase @Inject constructor(
    private val repository: CargoRepository
) : BaseUseCase<Unit, List<Cargo>> {

    override suspend fun execute(param: Unit): List<Cargo> {
        return repository.getAllCargos()
    }
}
