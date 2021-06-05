package eu.tribe.di

import eu.tribe.Presentation.Fragments.viewModel.CityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CityViewModel() }
    // задекларировать интерактор и репозиторий и дата соурс для репозитория
    // Rx
}