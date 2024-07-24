package com.bta.retotecnico.data

import com.bta.retotecnico.data.cloud.CartasService
import com.bta.retotecnico.data.cloud.model.response.ConsultaEstadoJuegoCloudResponse
import com.bta.retotecnico.domain.model.ConsultaEstadoJuego
import com.bta.retotecnico.domain.model.toDomain

import javax.inject.Inject

class EstadoJuegoRepository @Inject constructor(
    private val api: CartasService,
) {
    suspend fun consultaEstado(
    ): ConsultaEstadoJuego {
        val response: ConsultaEstadoJuegoCloudResponse = api.consultaEstado()
        return response.toDomain()
    }
}