package com.fakher.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "FORM", primaryKeys = ["int1", "int2", "str1", "str2"])
data class FormPersistence(
    @ColumnInfo(name = "int1")
    val firstInt: Int,
    @ColumnInfo(name = "int2")
    val secondInt: Int,
    @ColumnInfo(name = "str1")
    val firstWord: String,
    @ColumnInfo(name = "str2")
    val secondWord: String,
    @ColumnInfo(name = "hits")
    val hits: Int = 0
)