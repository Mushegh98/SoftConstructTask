package com.softconstruct.entity.uimodel

import com.softconstruct.entity.responsemodel.Fields
import java.io.Serializable

data class ArticleUI(
    var id: String,
    var fields: Fields?,
    var webTitle: String?,
    var type: String?,
    var isFavorite: Boolean
): Serializable
