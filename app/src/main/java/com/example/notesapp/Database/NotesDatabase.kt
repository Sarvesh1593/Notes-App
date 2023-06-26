package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

import com.example.notesapp.NotesDao.NotesDao
import com.example.notesapp.NotesEntity.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun myDao() : NotesDao



    companion object{

        @Volatile
        var INSTANCE : NotesDatabase?= null

        fun getDatabaseInstance(context: Context):NotesDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()

                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }
    }
}