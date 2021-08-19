package com.softconstruct.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softconstruct.data.dataservice.sqlservice.dao.ArticleDao
import com.softconstruct.data.dataservice.sqlservice.dao.FavoriteArticleDao
import com.softconstruct.entity.roommodel.Article
import com.softconstruct.entity.roommodel.Details
import com.softconstruct.entity.roommodel.FavoriteArticle

@Database(
    entities = [Details::class, Article::class, FavoriteArticle::class],
    version = 3,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun favoriteArticleDao(): FavoriteArticleDao
}