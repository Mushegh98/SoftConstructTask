package com.softconstruct.entity.responsemodel


import com.squareup.moshi.Json

data class ArticleDTO(
    @Json(name = "response")
    var response: Response?
)