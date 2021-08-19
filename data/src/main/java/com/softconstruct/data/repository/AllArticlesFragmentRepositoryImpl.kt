package com.softconstruct.data.repository

import com.softconstruct.data.dataservice.RetrofitService
import com.softconstruct.data.dataservice.sqlservice.dao.ArticleDao
import com.softconstruct.data.datastore.AllArticlesFragmentRepository
import com.softconstruct.data.utils.analyzeResponse
import com.softconstruct.data.utils.makeApiCall
import com.softconstruct.entity.roommodel.Article
import kotlinx.coroutines.flow.Flow

internal class AllArticlesFragmentRepositoryImpl(private val retrofitService: RetrofitService, private val articleDao: ArticleDao): AllArticlesFragmentRepository {

    override suspend fun getArticles() = makeApiCall({
        analyzeResponse(
            retrofitService.getArticles("d4730e21-750c-464d-9115-9d328e453aa8",1,10,"thumbnail")
        )
    })

    override fun getArticlesFromDB(): Flow<List<Article>> = articleDao.getArticles()

    override suspend fun insertArticles(articles: List<Article>) = articleDao.saveArticleList(articles)
}