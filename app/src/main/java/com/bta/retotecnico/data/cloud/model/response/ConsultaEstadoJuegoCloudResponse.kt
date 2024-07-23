package com.bta.retotecnico.data.cloud.model.response

import com.google.gson.annotations.SerializedName

data class ConsultaEstadoJuegoCloudResponse(
    @SerializedName("state") val estado: String
)