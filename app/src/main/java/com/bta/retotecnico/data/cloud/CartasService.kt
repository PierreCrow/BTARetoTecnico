package com.bta.retotecnico.data.cloud

import com.bta.retotecnico.data.cloud.model.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

class CartasService @Inject constructor(private val api: DataServiceCartasApiClientWS) {

    suspend fun consultaEstado(
    ): Result<ConsultaEstadoJuegoCloudResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.consultaEstadoJuego()
            response.onSuccess {
                response
            }.onFailure {
                it.message
            }
        }
    }

}