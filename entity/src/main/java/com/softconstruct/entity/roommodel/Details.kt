package com.softconstruct.entity.roommodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Details")
data class Details(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)