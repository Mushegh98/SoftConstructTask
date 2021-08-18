package com.softconstruct.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softconstruct.data.dataservice.sqlservice.dao.DetailsDao
import com.softconstruct.entity.roommodel.Details

@Database(
    entities = [Details::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun detailsDao(): DetailsDao
}