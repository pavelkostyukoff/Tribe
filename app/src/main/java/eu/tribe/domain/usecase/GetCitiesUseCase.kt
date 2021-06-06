package eu.tribe.domain.usecase

import eu.tribe.data.CitiesRepository
import eu.tribe.domain.model.CityEntity
import kotlinx.coroutines.flow.Flow

class GetCitiesUseCase(private val repository: CitiesRepository) {

    fun getCities(): Flow<List<CityEntity>> {
        return repository.getCities()
    }
}