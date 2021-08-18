package com.softconstruct.entity

sealed class Result<out S> {
    data class Success<S>(val data: S?) : Result<S>()
    data class Error<E>(val errors: GuardianTaskException) : Result<E>()
}