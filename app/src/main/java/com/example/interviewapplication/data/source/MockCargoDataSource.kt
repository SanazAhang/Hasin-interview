package com.example.interviewapplication.data.source

import com.example.interviewapplication.data.source.mock.CargoDataSource
import com.example.interviewapplication.domain.model.Cargo
import javax.inject.Inject

class MockCargoDataSource @Inject constructor() : CargoDataSource {
    override suspend fun getCargos(): List<Cargo> {
        return listOf(
            Cargo(1, "تهران", "اصفهان", "2 تن", "5 میلیون"),
            Cargo(2, "شیراز", "تبریز", "1 تن", "3 میلیون"),
            Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون")
        )
    }
}
