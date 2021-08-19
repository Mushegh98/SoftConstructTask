package com.softconstruct.data.dataservice.sqlservice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softconstruct.entity.roommodel.Article
import com.softconstruct.entity.roommodel.Details
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticleList(articles: List<Article>)

}