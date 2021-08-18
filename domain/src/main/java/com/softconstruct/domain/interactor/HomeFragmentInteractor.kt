package com.softconstruct.domain.interactor

import com.softconstruct.entity.responsemodel.ArticleDTO

interface HomeFragmentInteractor {
    suspend fun getArticles(): com.softconstruct.entity.Result<ArticleDTO>
}