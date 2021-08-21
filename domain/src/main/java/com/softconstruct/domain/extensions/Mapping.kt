package com.softconstruct.domain.extensions

import com.softconstruct.entity.responsemodel.Result
import com.softconstruct.entity.roommodel.Article
import com.softconstruct.entity.roommodel.FavoriteArticle
import com.softconstruct.entity.uimodel.ArticleUI

fun Result.fromResponseToRoomModel(isFavorite: Boolean) = Article(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type,
    isFavorite = isFavorite
)

fun Article.fromRoomModelToUI() = ArticleUI(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type,
    isFavorite = isFavorite
)

fun FavoriteArticle.fromRoomModelToUI() = ArticleUI(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type,
    isFavorite = true
)

fun ArticleUI.fromUIToRoomModelFavoriteArticle() = FavoriteArticle(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type
)

fun ArticleUI.fromUIToRoomModelArticle() = Article(
    id = id,
    fields = fields,
    webTitle = webTitle,
    type = type,
    isFavorite = isFavorite
)