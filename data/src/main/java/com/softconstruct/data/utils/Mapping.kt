package com.softconstruct.data.utils

import com.softconstruct.entity.responsemodel.Result
import com.softconstruct.entity.roommodel.Article
import com.softconstruct.entity.roommodel.FavoriteArticle
import com.softconstruct.entity.uimodel.ArticleUI

fun Result.fromResponseToRoomModel() = Article(
        id = id,
        fields = fields,
        webTitle = webTitle,
        type = type
    )

fun Article.fromRoomModelToUI() = ArticleUI(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type
)

fun FavoriteArticle.fromRoomModelToUI() = ArticleUI(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type
)

fun ArticleUI.fromUIToRoomModel() = FavoriteArticle(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type
)