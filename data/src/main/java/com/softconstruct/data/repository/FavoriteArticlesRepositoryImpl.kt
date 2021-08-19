package com.softconstruct.data.repository

import com.softconstruct.data.dataservice.sqlservice.dao.FavoriteArticleDao
import com.softconstruct.data.datastore.FavoriteArticlesRepository
import com.softconstruct.entity.roommodel.FavoriteArticle
import kotlinx.coroutines.flow.Flow

internal class FavoriteArticlesRepositoryImpl(private val favoriteArticleDao: FavoriteArticleDao): FavoriteArticlesRepository {

    override fun getArticlesFromDB(): Flow<List<FavoriteArticle>> = favoriteArticleDao.getFavoriteArticles()

    override suspend fun insertArticles(articles: List<FavoriteArticle>) = favoriteArticleDao.saveFavoriteArticleList(articles)

    override suspend fun insertFavoriteArticle(article: FavoriteArticle) = favoriteArticleDao.saveFavoriteArticle(article)
}