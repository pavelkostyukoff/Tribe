package eu.tribe.ui.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tribe.domain.usecase.GetCitiesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CitiesViewModel(private val useCase: GetCitiesUseCase) :ViewModel() {

    private val stateInternal = MutableLiveData<CitiesState>(CitiesState.Loading)
    val state : LiveData<CitiesState> = stateInternal

    init {
        viewModelScope.launch {
            useCase.getCities()
                    .map {
                        CitiesState.Success(it) as CitiesState
                    }
                    .catch {
                        emit(CitiesState.Error)
                    }
                    .collect{
                stateInternal.value = it
            }
        }
    }
}