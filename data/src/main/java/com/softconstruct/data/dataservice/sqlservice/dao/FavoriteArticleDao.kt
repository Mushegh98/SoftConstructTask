package com.softconstruct.data.dataservice.sqlservice.dao

import androidx.room.*
import com.softconstruct.entity.roommodel.FavoriteArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {

    @Query("SELECT * FROM FavoriteArticle")
    fun getFavoriteArticles(): Flow<List<FavoriteArticle>>

    @Query("SELECT id FROM FavoriteArticle")
    suspend fun getArticlesIdFromDB(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteArticle(article: FavoriteArticle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteArticleList(articles: List<FavoriteArticle>)

    @Delete
    suspend fun deleteFavoriteArticle(favoriteArticle: FavoriteArticle)
}