package com.nadillla.tabunganapp.Local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id : Int? = null,

    @ColumnInfo(name = "user_name")
    val user_name : String,

    @ColumnInfo(name = "user_email")
    val user_email : String,

    @ColumnInfo(name = "user_password")
    val user_password : String




)
