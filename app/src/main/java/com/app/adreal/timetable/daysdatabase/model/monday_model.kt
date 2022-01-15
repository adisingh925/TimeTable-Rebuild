package com.app.adreal.timetable.daysdatabase.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "monday")
data class monday_model(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "start_time") val starttime : Int,
    @ColumnInfo(name = "end_time") val endtime : Int,
    @ColumnInfo(name = "subject") val subject : String,
)