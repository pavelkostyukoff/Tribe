package eu.tribe.ui.cities

import eu.tribe.domain.model.CityEntity

sealed class CitiesState {
    object Loading : CitiesState()
    data class Success(val cities: List<CityEntity>) : CitiesState()
    object Error : CitiesState()
}