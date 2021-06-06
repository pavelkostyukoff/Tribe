package eu.tribe.data

import eu.tribe.domain.model.CityEntity
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun getCities() : Flow<List<CityEntity>>
}