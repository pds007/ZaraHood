package com.example.zarahood.utils.processingStates

sealed class State<T> {
    data class Loading<T>(var loading: Boolean) : State<T>()
    data class Data<T>(var data: T) : State<T>()
    data class Error<T>(val e: Throwable) : State<T>()

    companion object {
        fun <T> publishLoading(isLoading: Boolean = true): State<T> = Loading(isLoading)

        fun <T> publishData(data: T): State<T> = Data(data)

        fun <T> publishError(e: Throwable): State<T> = Error(e)
    }
}