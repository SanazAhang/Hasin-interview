package com.example.interviewapplication.data.repository

import com.example.interviewapplication.data.source.mock.CargoDataSource
import com.example.interviewapplication.domain.model.Cargo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

@ExperimentalCoroutinesApi
class CargoRepositoryImplTest {

    private lateinit var cargoRepository: CargoRepositoryImpl
    private val dataSource: CargoDataSource = mockk()

    @Before
    fun setup() {
        // شبیه‌سازی CargoDataSource برای تست
        cargoRepository = CargoRepositoryImpl(dataSource)
    }

    @Test
    fun `getAllCargos should return list of cargos from dataSource`() = runTest {
        // Given: یک لیست از Cargos داریم که توسط dataSource برگشت داده می‌شود
        val cargos = listOf(
            Cargo(1, "تهران", "اصفهان", "2 تن", "5 میلیون", "سیمان", "گونی", "۱۴۰۳/۲/۳۷"),
            Cargo(2, "شیراز", "تبریز", "1 تن", "3 میلیون", "کاغذ", "گونی", "۱۴۰۳/۲/۳۱")
        )

        // شبیه‌سازی رفتار getCargos برای برگشت دادن لیست cargos
        coEvery { dataSource.getCargos() } returns cargos

        // When: متد getAllCargos در Repository فراخوانی می‌شود
        val result = cargoRepository.getAllCargos()

        // Then: باید لیست cargos برگشت داده شود
        assertEquals(cargos, result)
    }

    @Test
    fun `getAllCargos should return empty list when dataSource returns empty list`() = runTest {
        // Given: dataSource یک لیست خالی برگشت می‌دهد
        val cargos = emptyList<Cargo>()

        // شبیه‌سازی رفتار getCargos برای برگشت دادن لیست خالی
        coEvery { dataSource.getCargos() } returns cargos

        // When: متد getAllCargos در Repository فراخوانی می‌شود
        val result = cargoRepository.getAllCargos()

        // Then: باید لیست خالی برگشت داده شود
        assertEquals(cargos, result)
    }
}