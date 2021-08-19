package com.softconstruct.domain.usecase

import com.softconstruct.data.datastore.AllArticlesFragmentRepository
import com.softconstruct.data.utils.fromResponseToRoomModel
import com.softconstruct.data.utils.fromRoomModelToUI
import com.softconstruct.domain.interactor.AllArticlesFragmentInteractor
import com.softconstruct.entity.responsemodel.ArticleDTO
import com.softconstruct.entity.roommodel.Article
import com.softconstruct.entity.uimodel.ArticleUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AllArticlesFragmentUseCase(private val allArticlesFragmentRepository: AllArticlesFragmentRepository): AllArticlesFragmentInteractor {

    override suspend fun getArticles(): com.softconstruct.entity.Result<Unit> {
        return when (val result = allArticlesFragmentRepository.getArticles()) {
            is com.softconstruct.entity.Result.Success -> {
                result.data?.response?.results?.map { it.fromResponseToRoomModel() }?.let {
                    allArticlesFragmentRepository.insertArticles(
                        it
                    )
                }
                com.softconstruct.entity.Result.Success(Unit)
            }
            is com.softconstruct.entity.Result.Error -> {
                com.softconstruct.entity.Result.Error(result.errors)
            }
        }
    }

    override fun getArticlesFromDB(): Flow<List<Article>>{
        return allArticlesFragmentRepository.getArticlesFromDB()
    }

    override fun getArticleUIMapper(article: List<Article>): List<ArticleUI>{
        return article.map { it.fromRoomModelToUI() }
    }

}