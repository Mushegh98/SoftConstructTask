package com.softconstruct.domain.usecase

import com.softconstruct.data.datastore.FavoriteArticlesRepository
import com.softconstruct.data.utils.fromRoomModelToUI
import com.softconstruct.data.utils.fromUIToRoomModel
import com.softconstruct.domain.interactor.FavoriteArticlesInteractor
import com.softconstruct.entity.roommodel.FavoriteArticle
import com.softconstruct.entity.uimodel.ArticleUI
import kotlinx.coroutines.flow.Flow

internal class FavoriteArticlesUseCase(private val favoriteArticlesRepository: FavoriteArticlesRepository): FavoriteArticlesInteractor {

    override fun getArticlesFromDB(): Flow<List<FavoriteArticle>> = favoriteArticlesRepository.getArticlesFromDB()

    override suspend fun insertArticles(articles: List<FavoriteArticle>) = favoriteArticlesRepository.insertArticles(articles)

    override fun getArticleUIMapper(article: List<FavoriteArticle>): List<ArticleUI>{
        return article.map { it.fromRoomModelToUI() }
    }

    override suspend fun insertFavoriteArticle(article: ArticleUI){
        favoriteArticlesRepository.insertFavoriteArticle(article.fromUIToRoomModel())
    }
}