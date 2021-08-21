package com.softconstruct.data.utils

import android.util.Log
import com.softconstruct.entity.GuardianTaskException
import retrofit2.Response


suspend fun <R> makeApiCall(
    call: suspend () -> com.softconstruct.entity.Result<R>,
    errorCode: Int = 4567
) = try {
    call()
} catch (e: Exception) {
    Log.i("makeApiCall", "makeApiCall: ${e.message}")
    com.softconstruct.entity.Result.Error(GuardianTaskException(errorCode, e.message))
}

private const val HTTP_CODE_OK = 200

fun <R> analyzeResponse(response: Response<R>): com.softconstruct.entity.Result<R> {
    return when (response.code()) {
        HTTP_CODE_OK -> com.softconstruct.entity.Result.Success(response.body())
        else -> com.softconstruct.entity.Result.Error(
            GuardianTaskException(
                response.code(),
                response.message()
            )
        )
    }
}