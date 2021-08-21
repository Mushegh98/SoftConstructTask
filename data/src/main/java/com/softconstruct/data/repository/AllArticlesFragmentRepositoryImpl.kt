package com.softconstruct.data.repository

import com.softconstruct.data.dataservice.RetrofitService
import com.softconstruct.data.dataservice.sqlservice.dao.ArticleDao
import com.softconstruct.data.dataservice.sqlservice.dao.FavoriteArticleDao
import com.softconstruct.data.datastore.AllArticlesFragmentRepository
import com.softconstruct.data.utils.analyzeResponse
import com.softconstruct.data.utils.makeApiCall
import com.softconstruct.entity.roommodel.Article
import kotlinx.coroutines.flow.Flow

internal class AllArticlesFragmentRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val articleDao: ArticleDao,
    private val favoriteArticleDao: FavoriteArticleDao
) : AllArticlesFragmentRepository {

    override suspend fun getArticles(page: Int) = makeApiCall({
        analyzeResponse(
            retrofitService.getArticles(page)
        )
    })

    override suspend fun getArticlesIdFromDB(): List<String> =
        favoriteArticleDao.getArticlesIdFromDB()

    override fun getArticlesFromDB(): Flow<List<Article>> = articleDao.getArticles()

    override suspend fun insertArticles(articles: List<Article>) =
        articleDao.saveArticleList(articles)

    override suspend fun deleteAllArticles() = articleDao.deleteAllArticles()

    override suspend fun updateArticle(article: Article) =
        articleDao.updateArticle(article.isFavorite, article.id)
}