package com.softconstruct.data.dataservice.sqlservice.dao

import androidx.room.*
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

    @Query("DELETE FROM Article")
    suspend fun deleteAllArticles()

    @Query("UPDATE Article SET isFavorite = not :isFavorite WHERE id = :id")
    suspend fun updateArticle(isFavorite: Boolean, id: String)

}