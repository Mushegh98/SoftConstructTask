package com.softconstruct.domain.usecase

import com.softconstruct.data.datastore.AllArticlesFragmentRepository
import com.softconstruct.data.utils.fromResponseToRoomModel
import com.softconstruct.data.utils.fromRoomModelToUI
import com.softconstruct.domain.interactor.AllArticlesFragmentInteractor
import com.softconstruct.entity.GuardianTaskException
import com.softconstruct.entity.roommodel.Article
import com.softconstruct.entity.uimodel.ArticleUI
import kotlinx.coroutines.flow.Flow

internal class AllArticlesFragmentUseCase(private val allArticlesFragmentRepository: AllArticlesFragmentRepository): AllArticlesFragmentInteractor {

    private var pageNumber = 1
    private var allPages = 500

    override suspend fun getArticles(): com.softconstruct.entity.Result<Unit> {
        if(pageNumber > allPages) return com.softconstruct.entity.Result.Error(GuardianTaskException(4567,"No found page"))
        return when (val result = allArticlesFragmentRepository.getArticles(pageNumber)) {
            is com.softconstruct.entity.Result.Success -> {
                pageNumber++
                val favoriteArticlesIdFromDB = allArticlesFragmentRepository.getArticlesIdFromDB()
                result.data?.response?.results?.map { it.fromResponseToRoomModel(favoriteArticlesIdFromDB.contains(it.id)) }?.let {
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

    override suspend fun deleteAllArticles() = allArticlesFragmentRepository.deleteAllArticles()

}