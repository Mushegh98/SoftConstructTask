package com.softconstruct.task.fragment.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.softconstruct.domain.interactor.HomeFragmentInteractor
import com.softconstruct.entity.responsemodel.ArticleDTO
import com.softconstruct.task.base.viewmodel.BaseViewModel
import com.softconstruct.task.base.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel(private val homeFragmentInteractor: HomeFragmentInteractor): BaseViewModel() {

    private val _getArticlesSuccess by lazy { SingleLiveEvent<ArticleDTO>() }
    val getArticlesSuccess: LiveData<ArticleDTO> get() = _getArticlesSuccess
    private val _getArticlesError by lazy { SingleLiveEvent<String>() }
    val getArticlesError: LiveData<String> get() = _getArticlesError

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = homeFragmentInteractor.getArticles()) {
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
}