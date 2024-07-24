package com.bta.retotecnico.domain.model

import com.bta.retotecnico.data.cloud.model.response.ConsultaEstadoJuegoCloudResponse

class ConsultaEstadoJuego (val estado:Int)

fun ConsultaEstadoJuegoCloudResponse.toDomain() = ConsultaEstadoJuego(estado)