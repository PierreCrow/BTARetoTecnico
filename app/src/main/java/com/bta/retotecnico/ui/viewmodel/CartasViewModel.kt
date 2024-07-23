package com.bta.retotecnico.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bta.retotecnico.data.cloud.model.response.ConsultaEstadoJuegoCloudResponse
import com.bta.retotecnico.usercases.CartasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartasViewModel @Inject constructor(
    private val cartasUseCase: CartasUseCase
) : ViewModel() {

    val responseConsultaEstado = MutableLiveData<ConsultaEstadoJuegoCloudResponse>()
    val isLoading = MutableLiveData<Boolean>()
    val cartaState = MutableLiveData<CartasState>(CartasState.START)

    fun consultaEstadoJuego(
    ) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = cartasUseCase.consultaEstado(
            ).onSuccess {
                isLoading.postValue(false)
                cartaState.postValue(CartasState.SUCCESS)
                responseConsultaEstado.postValue(it)
            }.onFailure {
                isLoading.postValue(false)
                cartaState.postValue(CartasState.FAILURE(it.cause!!.message))
            }
        }
    }

    sealed class CartasState {
        object START : CartasState()
        object LOADING : CartasState()
        object SUCCESS : CartasState()
        data class FAILURE(val message: String?) : CartasState()
    }

}