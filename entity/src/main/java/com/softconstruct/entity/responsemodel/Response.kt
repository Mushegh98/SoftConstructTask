package com.softconstruct.entity.responsemodel


import com.squareup.moshi.Json

data class Response(
    @Json(name = "currentPage")
    var currentPage: Int?,
    @Json(name = "orderBy")
    var orderBy: String?,
    @Json(name = "pageSize")
    var pageSize: Int?,
    @Json(name = "pages")
    var pages: Int?,
    @Json(name = "results")
    var results: List<Result>?,
    @Json(name = "startIndex")
    var startIndex: Int?,
    @Json(name = "status")
    var status: String?,
    @Json(name = "total")
    var total: Int?,
    @Json(name = "userTier")
    var userTier: String?
)