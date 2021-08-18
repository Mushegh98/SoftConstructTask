package com.softconstruct.domain.usecase

import com.softconstruct.data.datastore.HomeFragmentRepository
import com.softconstruct.domain.interactor.HomeFragmentInteractor
import com.softconstruct.entity.responsemodel.ArticleDTO

internal class HomeFragmentUseCase(private val homeFragmentRepository: HomeFragmentRepository): HomeFragmentInteractor {

    override suspend fun getArticles(): com.softconstruct.entity.Result<ArticleDTO> {
        return when (val result = homeFragmentRepository.getArticles()) {
            is com.softconstruct.entity.Result.Success -> {
                com.softconstruct.entity.Result.Success(result.data)
            }
            is com.softconstruct.entity.Result.Error -> {
                com.softconstruct.entity.Result.Error(result.errors)
            }
        }
    }
}