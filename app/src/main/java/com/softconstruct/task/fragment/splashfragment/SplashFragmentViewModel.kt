package com.softconstruct.task.fragment.splashfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.softconstruct.domain.interactor.AllArticlesFragmentInteractor
import com.softconstruct.task.base.viewmodel.BaseViewModel
import com.softconstruct.task.base.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragmentViewModel(private val allArticlesFragmentInteractor: AllArticlesFragmentInteractor) : BaseViewModel() {

    private val _goToHome by lazy { SingleLiveEvent<Unit>() }
    val goToHome: LiveData<Unit> get() = _goToHome

    fun goToHomePage() {
        viewModelScope.launch {
            delay(2000)
            _goToHome.value = Unit
        }
    }

    fun deleteAllArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            allArticlesFragmentInteractor.deleteAllArticles()
        }
    }
}