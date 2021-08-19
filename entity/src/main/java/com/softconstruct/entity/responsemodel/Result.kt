package com.softconstruct.entity.responsemodel


import com.squareup.moshi.Json

data class Result(
    @Json(name = "apiUrl")
    var apiUrl: String?,
    @Json(name = "fields")
    var fields: Fields?,
    @Json(name = "id")
    var id: String,
    @Json(name = "isHosted")
    var isHosted: Boolean?,
    @Json(name = "pillarId")
    var pillarId: String?,
    @Json(name = "pillarName")
    var pillarName: String?,
    @Json(name = "sectionId")
    var sectionId: String?,
    @Json(name = "sectionName")
    var sectionName: String?,
    @Json(name = "type")
    var type: String?,
    @Json(name = "webPublicationDate")
    var webPublicationDate: String?,
    @Json(name = "webTitle")
    var webTitle: String?,
    @Json(name = "webUrl")
    var webUrl: String?
)