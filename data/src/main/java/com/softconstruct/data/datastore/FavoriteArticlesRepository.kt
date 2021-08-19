package com.softconstruct.data.datastore

import com.softconstruct.entity.roommodel.FavoriteArticle
import kotlinx.coroutines.flow.Flow

interface FavoriteArticlesRepository {
    fun getArticlesFromDB(): Flow<List<FavoriteArticle>>
    suspend fun insertArticles(articles: List<FavoriteArticle>)
    suspend fun insertFavoriteArticle(article: FavoriteArticle)
    suspend fun deleteFavoriteArticle(favoriteArticle: FavoriteArticle)
}