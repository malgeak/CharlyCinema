package com.malgeakstudios.charlycinema.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


data class FormattedResponse<out T>(
    val status: Status,
    val data : T?,
    val message : String?
) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> loading(data: T? = null): FormattedResponse<T> {
            return FormattedResponse(Status.LOADING, data, null)
        }

        fun <T> success(data: T): FormattedResponse<T> {
            return FormattedResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): FormattedResponse<T> {
            return FormattedResponse(Status.ERROR, data, message)
        }
    }
}

fun <T, A> getBestResponse(
    localSource: () -> LiveData<T>,
    remoteSource: suspend () -> FormattedResponse<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<FormattedResponse<T>> =
    liveData(Dispatchers.IO) {
        emit(FormattedResponse.loading())
        val source = localSource.invoke().map { FormattedResponse.success(it) }
        emitSource(source)

        val responseStatus = remoteSource.invoke()
        if (responseStatus.status == FormattedResponse.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == FormattedResponse.Status.ERROR) {
            emit(FormattedResponse.error(responseStatus.message!!))
            emitSource(source)
        }
    }