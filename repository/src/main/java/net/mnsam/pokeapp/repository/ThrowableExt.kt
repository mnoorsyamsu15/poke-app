package net.mnsam.pokeapp.repository

import retrofit2.HttpException

typealias IOException = java.io.IOException

fun Throwable.parseRetrofitError(): String {
    return when (this) {
        is HttpException -> {
            val errorMessage = try {
                this.response()?.errorBody()?.string() ?: "Failed to read content"
            } catch (ignored: IOException) {
                "Unknown API error"
            }
            /* return */ errorMessage
        }
        is IOException -> "Connection failed"
        else -> "Unknown error occurred: ${this.message}"
    }
}
