package com.example.interviewapplication.domain.usecase.base

import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.domain.repository.CargoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetAllCargosUseCaseTest {

    private lateinit var getAllCargosUseCase: GetAllCargosUseCase
    private val cargoRepository: CargoRepository = mockk()

    @Before
    fun setup() {
        getAllCargosUseCase = GetAllCargosUseCase(cargoRepository)
    }

    @Test
    fun `execute should return list of cargos from repository`() = runTest {
        // Given
        val mockCargos = listOf(
        Cargo(
            0,
            "تهران",
            "اصفهان",
            "2 تن",
            "5 میلیون",
            "سیمان",
            "گونی",
            "۱۴۰۳/۲/۳۷",
            isAccepted = false
        ),
        Cargo(
            1,
            "شیراز",
            "تبریز",
            "1 تن",
            "3 میلیون",
            "کاغذ",
            "گونی",
            "۱۴۰۳/۲/۳۱",
            isAccepted = true
        ),
        Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون", "یخچال", "کارتن", "۱۴۰۳/۲/۱۲")
    )

        // Mock کردن متد getAllCargos در ریپازیتوری
        coEvery { cargoRepository.getAllCargos() } returns mockCargos

        // When
        val result = getAllCargosUseCase.execute(Unit)

        // Then
        assertEquals(mockCargos, result) // نتیجه باید همون لیستی باشه که mock کردیم
    }
}
