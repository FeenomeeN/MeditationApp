package com.bsuir.meditationapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") val id: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "is_authenticated") val isAuthenticated: Boolean,
    @ColumnInfo(name = "user_weight") val weight: Int = -1,
    @ColumnInfo(name = "user_height") val height: Int = -1
)