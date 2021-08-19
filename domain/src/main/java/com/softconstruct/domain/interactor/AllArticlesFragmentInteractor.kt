package com.softconstruct.domain.interactor

import com.softconstruct.entity.roommodel.Article
import com.softconstruct.entity.uimodel.ArticleUI
import kotlinx.coroutines.flow.Flow

interface AllArticlesFragmentInteractor {
    suspend fun getArticles(): com.softconstruct.entity.Result<Unit>
    fun getArticlesFromDB(): Flow<List<Article>>
    fun getArticleUIMapper(article: List<Article>): List<ArticleUI>
}