package com.app.adreal.timetable.daysdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.adreal.timetable.daysdatabase.dao.dayDao
import com.app.adreal.timetable.daysdatabase.model.dayModel
import com.app.adreal.timetable.daysdatabase.model.taskModel

@Database(entities = [dayModel::class,taskModel::class], version = 1, exportSchema = false)
abstract class user_database : RoomDatabase() {

    abstract fun dayDao() : dayDao

    companion object{

        @Volatile
        private var INSTANCE : user_database? = null

        fun getDatabase(context: Context): user_database
        {
            val tempInstance = INSTANCE
            if(tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(context.applicationContext,
                    user_database::class.java,
                    "user_database").build()

                INSTANCE = instance
                return instance
            }
        }
    }
}