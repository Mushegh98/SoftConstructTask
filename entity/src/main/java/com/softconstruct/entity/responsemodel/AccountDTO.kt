package com.softconstruct.entity.responsemodel

import com.squareup.moshi.Json

data class AccountDTO(
    @field:Json(name = "fname")
    val firstName: String? = null
)