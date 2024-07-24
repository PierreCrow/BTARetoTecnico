package com.bta.retotecnico.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bta.retotecnico.domain.model.ConsultaEstadoJuego
import com.bta.retotecnico.domain.util.Constans
import com.bta.retotecnico.usercases.CartasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartasViewModel @Inject constructor(
    private val cartasUseCase: CartasUseCase
) : ViewModel() {

    val responseConsultaEstado = MutableLiveData<ConsultaEstadoJuego>()
    val isLoading = MutableLiveData<Boolean>()
    val cartaState = MutableLiveData<CartasState>(CartasState.START)

    fun consultaEstadoJuego() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = cartasUseCase()
            if (result != null) {
                isLoading.postValue(false)
                cartaState.postValue(CartasState.SUCCESS)
                responseConsultaEstado.postValue(result)
            } else {
                isLoading.postValue(false)
                cartaState.postValue(CartasState.FAILURE(Constans.ERROR.ERROR))
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