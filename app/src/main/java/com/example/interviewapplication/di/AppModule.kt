package com.example.interviewapplication.di

import com.example.interviewapplication.data.source.mock.CargoDataSource
import com.example.interviewapplication.data.repository.CargoRepositoryImpl
import com.example.interviewapplication.data.source.MockCargoDataSource
import com.example.interviewapplication.domain.repository.CargoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideCargoDataSource(): CargoDataSource = MockCargoDataSource()

    @Provides
    fun provideCargoRepository(dataSource: CargoDataSource): CargoRepository =
        CargoRepositoryImpl(dataSource = dataSource)
}
