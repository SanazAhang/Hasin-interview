package com.example.interviewapplication.presentation.viewmodel

import android.util.Log
import org.junit.jupiter.api.Assertions.*

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.mutableStateOf
import com.example.interviewapplication.domain.model.Cargo
import com.example.interviewapplication.domain.usecase.base.GetAllCargosUseCase
import com.example.interviewapplication.presentation.viewmodel.CargoViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class CargoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CargoViewModel
    private val fakeUseCase = mockk<GetAllCargosUseCase>()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        coEvery { fakeUseCase.execute(Unit) } returns emptyList()
        viewModel = CargoViewModel(fakeUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onCancelCargo should set isAccepted to false and selectedCargoId to null`() = runTest {
        // Given
        val cargos = listOf(
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

        // تنظیم داده‌های تست
        viewModel.setCargoListForTesting(cargos)
        // When
        viewModel.onCancelCargo(1)

        // Wait for coroutine to complete
        advanceUntilIdle()
        // Then
        val updatedList = viewModel.cargoList.value
        // بررسی وضعیت cargo[1] که باید isAccepted به false تغییر کند
        assertFalse(updatedList[1].isAccepted)

        // بررسی وضعیت cargo[0] که باید بدون تغییر باقی بماند
        assertFalse(updatedList[0].isAccepted)

        // بررسی اینکه selectedCargoId به null تغییر کند
        assertNull(viewModel.selectedCargoId.value)
    }


    @Test
    fun `acceptCargo should set isAccepted true for selected and false for others`() = runTest {
        // Given
        val cargos = listOf(
            Cargo(
                2,
                "تهران",
                "اصفهان",
                "2 تن",
                "5 میلیون",
                "سیمان",
                "گونی",
                "۱۴۰۳/۲/۳۷",
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
            ),
            Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون", "یخچال", "کارتن", "۱۴۰۳/۲/۱۲")
        )

        viewModel.setCargoListForTesting(cargos)

        // When
        viewModel.acceptCargo(2)
        advanceUntilIdle()

        // Then
        val updated = viewModel.cargoList.value
        assertFalse(updated[2].isAccepted)
        assertTrue(updated[0].isAccepted)
        assertEquals(2, viewModel.selectedCargoId.value)
    }


    @Test
    fun `onItemClick should update detail state with selected cargo`() = runTest {
        // Given
       val cargo = Cargo(
            1,
            "شیراز",
            "تبریز",
            "1 تن",
            "3 میلیون",
            "کاغذ",
            "گونی",
            "۱۴۰۳/۲/۳۱",
        )

        // When
        viewModel.onItemClick(cargo)
        advanceUntilIdle()

        // Then
        val detail = viewModel.detail.value
        assertTrue(detail.isVisible)
        assertEquals(cargo, detail.selectedCargo)
    }

    @Test
    fun `dismissBottomSheet should hide bottom sheet`() = runTest {
        // First show it
        val cargo = Cargo(
            1,
            "شیراز",
            "تبریز",
            "1 تن",
            "3 میلیون",
            "کاغذ",
            "گونی",
            "۱۴۰۳/۲/۳۱",
        )
        viewModel.onItemClick(cargo)
        advanceUntilIdle()

        // When
        viewModel.dismissBottomSheet()
        advanceUntilIdle()

        // Then
        assertFalse(viewModel.detail.value.isVisible)
    }

    @Test
    fun `getCargos should fetch cargos from use case and update cargoList`() = runTest {
        // Given
        val expectedCargos = listOf(
            Cargo(
                2,
                "تهران",
                "اصفهان",
                "2 تن",
                "5 میلیون",
                "سیمان",
                "گونی",
                "۱۴۰۳/۲/۳۷",
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
            ),
            Cargo(3, "مشهد", "کرج", "3 تن", "6 میلیون", "یخچال", "کارتن", "۱۴۰۳/۲/۱۲")
        )
        coEvery { fakeUseCase.execute(Unit) } returns expectedCargos

        // When
        viewModel.getCargos()
        advanceUntilIdle()

        // Then
        val actualCargos = viewModel.cargoList.value
        assertEquals(expectedCargos, actualCargos)
    }
}
