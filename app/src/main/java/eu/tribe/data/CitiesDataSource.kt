package eu.tribe.data

import eu.tribe.domain.model.CityEntity

interface CitiesDataSource {
    suspend fun getCities(): List<CityEntity>
}