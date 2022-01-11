package com.app.adreal.timetable.daysdatabase.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_info")
data class day_model(
@PrimaryKey val id : Int,
@ColumnInfo(name = "name") val name : String,
@ColumnInfo(name = "email") val email : String,
@ColumnInfo(name = "photo") val photo : Uri
)