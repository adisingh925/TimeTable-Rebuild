package com.app.adreal.timetable.userdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [user_model::class], version = 1, exportSchema = false)
abstract class user_database : RoomDatabase() {

    abstract fun userdao() : userDao

    companion object{

        @Volatile
        private var INSTANCE : user_database? = null

        fun getDatabase(context: Context):user_database
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