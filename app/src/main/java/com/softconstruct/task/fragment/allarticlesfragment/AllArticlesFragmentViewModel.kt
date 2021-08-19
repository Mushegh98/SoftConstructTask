package com.softconstruct.task.fragment.allarticlesfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.softconstruct.domain.interactor.AllArticlesFragmentInteractor
import com.softconstruct.domain.interactor.FavoriteArticlesInteractor
import com.softconstruct.entity.responsemodel.ArticleDTO
import com.softconstruct.entity.roommodel.FavoriteArticle
import com.softconstruct.entity.uimodel.ArticleUI
import com.softconstruct.task.base.viewmodel.BaseViewModel
import com.softconstruct.task.base.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllArticlesFragmentViewModel(private val allArticlesFragmentInteractor: AllArticlesFragmentInteractor,
                                   private val favoriteArticlesInteractor: FavoriteArticlesInteractor): BaseViewModel() {

    private val _getArticlesSuccess by lazy { SingleLiveEvent<Unit>() }
    val getArticlesSuccess: LiveData<Unit> get() = _getArticlesSuccess
    private val _getArticlesError by lazy { SingleLiveEvent<String>() }
    val getArticlesError: LiveData<String> get() = _getArticlesError

    val allArticleLiveData: LiveData<List<ArticleUI>> = allArticlesFragmentInteractor.getArticlesFromDB().map { allArticlesFragmentInteractor.getArticleUIMapper(it) }.asLiveData()

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = allArticlesFragmentInteractor.getArticles()) {
                is com.softconstruct.entity.Result.Success -> withContext(Dispatchers.Main) {
                    _getArticlesSuccess.value = result.data
                }
                is com.softconstruct.entity.Result.Error -> withContext(Dispatchers.Main) {
                    val errorMessage = result.errors.errorMessage
                    val errorCode = result.errors.errorCode
                    _getArticlesError.value = errorMessage
                }
            }

        }
    }

    fun saveFavorite(favoriteArticle: ArticleUI) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteArticlesInteractor.insertFavoriteArticle(favoriteArticle)
        }
    }

    fun deleteFavoriteArticle(favoriteArticle: ArticleUI) {
        viewModelScope.launch {
            favoriteArticlesInteractor.deleteFavoriteArticle(favoriteArticle)
        }
    }

    fun deleteAllArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            allArticlesFragmentInteractor.deleteAllArticles()
        }
    }
}