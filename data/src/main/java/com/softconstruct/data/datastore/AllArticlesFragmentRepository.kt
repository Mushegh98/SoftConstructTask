package com.softconstruct.data.datastore

import com.softconstruct.entity.Result
import com.softconstruct.entity.responsemodel.ArticleDTO
import com.softconstruct.entity.roommodel.Article
import kotlinx.coroutines.flow.Flow

interface AllArticlesFragmentRepository {
    suspend fun getArticles(): Result<ArticleDTO>
    fun getArticlesFromDB(): Flow<List<Article>>
    suspend fun insertArticles(articles: List<Article>)
}