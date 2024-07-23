package com.bta.retotecnico.usercases

import com.bta.retotecnico.data.EstadoJuegoRepository
import com.bta.retotecnico.data.cloud.model.response.ConsultaEstadoJuegoCloudResponse
import javax.inject.Inject

class CartasUseCase @Inject constructor(private val repository: EstadoJuegoRepository) {

    suspend fun consultaEstado(
    ): Result<ConsultaEstadoJuegoCloudResponse> {
        val respuesta = repository.consultaEstado(
        )
        return respuesta
    }
}