package com.app.adreal.timetable.daysdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class taskModel(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "Title") val title : String,
    @ColumnInfo(name = "Info") val info : String,
    @ColumnInfo(name = "Time") val time : String,
    @ColumnInfo(name = "Date") val date : String,
)