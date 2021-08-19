package com.softconstruct.entity.roommodel

import androidx.room.*
import com.softconstruct.entity.responsemodel.Fields
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@TypeConverters(FieldsTypeConverter::class)
@Entity
data class Article(
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


class  FieldsTypeConverter{
    @TypeConverter
    fun fromStringFieldsData(json: String): Fields? {
        val jsonAdapter: JsonAdapter<Fields> = Moshi.Builder().build().adapter(Fields::class.java)
        return jsonAdapter.fromJson(json)
    }

    @TypeConverter
    fun fromFieldsData(fieldData: Fields?): String {
        val jsonAdapter: JsonAdapter<Fields> = Moshi.Builder().build().adapter(Fields::class.java)
        return jsonAdapter.toJson(fieldData)
    }
}