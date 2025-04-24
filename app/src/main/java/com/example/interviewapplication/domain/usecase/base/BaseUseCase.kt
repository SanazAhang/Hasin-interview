package com.example.interviewapplication.domain.usecase.base

interface BaseUseCase<in Param, out Result> {
    suspend fun execute(param: Param): Result
}
