package com.softconstruct.entity.roommodel

import androidx.room.*
import com.softconstruct.entity.responsemodel.Fields

@TypeConverters(FieldsTypeConverter::class)
@Entity
data class FavoriteArticle(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: String,
    @ColumnInfo(name = "fields")
    var fields: Fields?,
    @ColumnInfo(name = "webTitle")
    var webTitle: String?,
    @ColumnInfo(name = "type")
    var type: String?,
)
