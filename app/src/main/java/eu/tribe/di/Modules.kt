package eu.tribe.di

import eu.tribe.ui.fragment.CitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module =  module {
    viewModel { CitiesViewModel() }
}