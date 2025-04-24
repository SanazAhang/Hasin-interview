package com.example.interviewapplication.data.source

import com.example.interviewapplication.data.source.mock.CargoDataSource
import com.example.interviewapplication.domain.model.Cargo
import javax.inject.Inject

class MockCargoDataSource @Inject constructor() : CargoDataSource {
    override suspend fun getCargos(): List<Cargo> {
        return listOf(
            Cargo(1, "تهران", "اصفهان", "2 تن", "5 میلیون","سیمان","گونی","۱۴۰۳/۲/۳۷"),
            Cargo(2, "شیراز", "تبریز", "1 تن", "3 میلیون","کاغذ","گونی","۱۴۰۳/۲/۳۱"),
            Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون","یخچال","کارتن","۱۴۰۳/۲/۱۲")
        )
    }
}
