package com.softconstruct.data.repository

import com.softconstruct.data.dataservice.RetrofitService
import com.softconstruct.data.datastore.HomeFragmentRepository
import com.softconstruct.data.utils.analyzeResponse
import com.softconstruct.data.utils.makeApiCall

internal class HomeFragmentRepositoryImpl(private val retrofitService: RetrofitService): HomeFragmentRepository {

    override suspend fun getArticles() = makeApiCall({
        analyzeResponse(
            retrofitService.getArticles("d4730e21-750c-464d-9115-9d328e453aa8",1,10)
        )
    })
}