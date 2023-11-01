package com.jl.blog.utils

object Constants {
    const val ERROR_TYPE = 1
    const val SUCCESS_TYPE = 2
    const val WARNING_TYPE = 3

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}