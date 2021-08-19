package com.softconstruct.data.dataservice.sqlservice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softconstruct.entity.roommodel.FavoriteArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {

    @Query("SELECT * FROM FavoriteArticle")
    fun getFavoriteArticles(): Flow<List<FavoriteArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteArticle(article: FavoriteArticle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteArticleList(articles: List<FavoriteArticle>)
}