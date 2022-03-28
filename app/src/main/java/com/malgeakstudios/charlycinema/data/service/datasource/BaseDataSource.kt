package com.malgeakstudios.charlycinema.data.service.datasource

import com.malgeakstudios.charlycinema.utils.FormattedResponse
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): FormattedResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let{ safeBody ->
                    return FormattedResponse.success(safeBody)
                }
            }
            return error(" ${response.code()} ---> ${response.body()}")
        } catch (mError: Exception) {
            return error(mError.message ?: mError.toString())
        }
    }

    private fun <T> error(message: String): FormattedResponse<T> {
        return FormattedResponse.error("La petición falló por la sigueinte razon: $message")
    }

}