package com.app.adreal.timetable.userdatabase

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
class user_model(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "name") val name : String?,
    @ColumnInfo(name = "email") val email : String?,
    @ColumnInfo(name = "phone_number") val number : String?,
    @ColumnInfo(name = "date_of_birth") val dob : String?,
    @ColumnInfo(name = "photo") val photo : String?
)