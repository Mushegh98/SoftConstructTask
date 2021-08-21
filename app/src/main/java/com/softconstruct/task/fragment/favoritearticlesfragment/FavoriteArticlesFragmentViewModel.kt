package com.softconstruct.task.fragment.favoritearticlesfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.softconstruct.domain.interactor.FavoriteArticlesInteractor
import com.softconstruct.entity.uimodel.ArticleUI
import com.softconstruct.task.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteArticlesFragmentViewModel(private val favoriteArticlesInteractor: FavoriteArticlesInteractor) :
    BaseViewModel() {

    val favoriteArticleLiveData: LiveData<List<ArticleUI>> =
        favoriteArticlesInteractor.getArticlesFromDB()
            .map { favoriteArticlesInteractor.getArticleUIMapper(it) }.asLiveData()

    fun deleteFavoriteArticle(favoriteArticle: ArticleUI) {
        viewModelScope.launch {
            favoriteArticlesInteractor.deleteFavoriteArticle(favoriteArticle)
        }
    }
}