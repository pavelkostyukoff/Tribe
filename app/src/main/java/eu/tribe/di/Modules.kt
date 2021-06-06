package eu.tribe.di

import eu.tribe.data.CitiesDataSource
import eu.tribe.data.CitiesRepository
import eu.tribe.data.CitiesRepositoryImpl
import eu.tribe.data.local.LocalDataSource
import eu.tribe.domain.usecase.GetCitiesUseCase
import eu.tribe.ui.cities.CitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val module =  module {
    viewModel { CitiesViewModel(get()) }
    single { LocalDataSource(get()) } bind CitiesDataSource::class
    single { CitiesRepositoryImpl(get()) } bind CitiesRepository::class
    factory { GetCitiesUseCase(get()) }
}