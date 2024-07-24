package com.bta.retotecnico.usercases

import android.util.Log
import com.bta.retotecnico.data.EstadoJuegoRepository
import com.bta.retotecnico.domain.model.ConsultaEstadoJuego
import javax.inject.Inject

class CartasUseCase @Inject constructor(private val repository: EstadoJuegoRepository) {
    suspend operator fun invoke(): ConsultaEstadoJuego {
        try {
            val respuesta = repository.consultaEstado()
            return respuesta
        }
        catch (e:Exception)
        {
         Log.e("CartasUseCase",e.message.toString())
            return TODO("Provide the return value")
        }
    }
}