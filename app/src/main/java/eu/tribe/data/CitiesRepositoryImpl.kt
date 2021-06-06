package eu.tribe.data

import eu.tribe.domain.model.CityEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CitiesRepositoryImpl(private val localDataSource: CitiesDataSource) : CitiesRepository {
    override fun getCities(): Flow<List<CityEntity>> {
        return flow {
            emit(localDataSource.getCities())
        }.flowOn(Dispatchers.IO)
    }
}