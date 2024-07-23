package com.bta.retotecnico.data.cloud

import com.bta.retotecnico.data.cloud.model.response.ConsultaEstadoJuegoCloudResponse
import com.bta.retotecnico.domain.util.Constans

import retrofit2.http.*

interface DataServiceCartasApiClientWS {

    @FormUrlEncoded
    @GET(Constans.URLS.CONSULTA_ESTADO_JUEGO)
    suspend fun consultaEstadoJuego(
    ): Result<ConsultaEstadoJuegoCloudResponse>

}