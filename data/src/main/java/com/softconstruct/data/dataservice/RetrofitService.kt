package com.softconstruct.data.dataservice


import com.softconstruct.entity.responsemodel.ArticleDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search")
    suspend fun getArticles(
        @Query(value = "api-key") apikey: String,
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int
    ): Response<ArticleDTO>
}