package com.softconstruct.data.dataservice


import com.softconstruct.entity.responsemodel.ArticleDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search")
    suspend fun getArticles(
        @Query("page") page: Int,
    ): Response<ArticleDTO>
}