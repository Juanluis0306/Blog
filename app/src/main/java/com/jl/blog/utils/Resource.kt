package com.jl.blog.utils

data class Resource<out T>(val status: Constants.Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Constants.Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String?): Resource<T> =
            Resource(status = Constants.Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Constants.Status.LOADING, data = data, message = null)
    }
}
