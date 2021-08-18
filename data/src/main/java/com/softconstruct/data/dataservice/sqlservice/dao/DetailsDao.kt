package com.softconstruct.data.dataservice.sqlservice.dao

import androidx.room.Dao
import androidx.room.Query
import com.softconstruct.entity.roommodel.Details

@Dao
interface DetailsDao {

    @Query("SELECT * FROM Details")
    suspend fun getDetails(): List<Details>
}