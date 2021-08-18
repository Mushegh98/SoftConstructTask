package com.softconstruct.data.datastore

import com.softconstruct.entity.Result
import com.softconstruct.entity.responsemodel.ArticleDTO

interface HomeFragmentRepository {
    suspend fun getArticles(): Result<ArticleDTO>
}