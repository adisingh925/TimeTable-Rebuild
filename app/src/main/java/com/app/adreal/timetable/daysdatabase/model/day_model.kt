package com.app.adreal.timetable.daysdatabase.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "day_info")
data class day_model(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "start_time") val starttime : Time,
    @ColumnInfo(name = "end_time") val endtime : Time,
    @ColumnInfo(name = "subject") val subject : String,
    @ColumnInfo(name = "remind") val state : Boolean,
    @ColumnInfo(name = "slidervalue") val slidervalue : Int
)