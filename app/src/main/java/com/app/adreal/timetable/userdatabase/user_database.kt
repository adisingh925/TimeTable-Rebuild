package com.app.adreal.timetable.userdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.adreal.timetable.daysdatabase.dao.*
import com.app.adreal.timetable.daysdatabase.model.*

@Database(entities = [user_model::class,monday_model::class,tuesday_model::class,wednesday_model::class,thursday_model::class,friday_model::class,saturday_model::class], version = 1, exportSchema = false)
abstract class user_database : RoomDatabase() {

    abstract fun userdao() : userDao

    abstract fun mondaydao() : mondayDao

    abstract fun tuesdaydao() : tuesdayDao

    abstract fun wednesdaydao() : wednesdayDao

    abstract fun thursdaydao() : thursdayDao

    abstract fun fridaydao() : fridayDao

    abstract fun saturdaydao() : saturdayDao

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