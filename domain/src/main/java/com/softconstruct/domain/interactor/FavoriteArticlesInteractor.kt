package com.softconstruct.domain.interactor

import com.softconstruct.entity.roommodel.FavoriteArticle
import com.softconstruct.entity.uimodel.ArticleUI
import kotlinx.coroutines.flow.Flow

interface FavoriteArticlesInteractor {
    fun getArticlesFromDB(): Flow<List<FavoriteArticle>>
    suspend fun insertArticles(articles: List<FavoriteArticle>)
    suspend fun insertFavoriteArticle(article: ArticleUI)
    fun getArticleUIMapper(article: List<FavoriteArticle>): List<ArticleUI>
    suspend fun deleteFavoriteArticle(article: ArticleUI)
}