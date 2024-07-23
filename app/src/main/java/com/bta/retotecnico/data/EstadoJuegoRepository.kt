package com.bta.retotecnico.data

import com.bta.retotecnico.data.cloud.CartasService
import com.bta.retotecnico.data.cloud.model.response.ConsultaEstadoJuegoCloudResponse

import javax.inject.Inject

class EstadoJuegoRepository @Inject constructor(
    private val api: CartasService,
) {
    suspend fun consultaEstado(
    ): Result<ConsultaEstadoJuegoCloudResponse> {
        val response: Result<ConsultaEstadoJuegoCloudResponse> = api.consultaEstado()
        return response
    }
}