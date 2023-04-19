package com.lafimsize.bilimnsanlarvebulular.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.model.Scientists

@Database(entities = arrayOf(Scientists::class,Inventions::class), version = 6)
abstract class ScientistsInventionsDatabase: RoomDatabase(){

    abstract fun scientistDao():IScientistsDao


    companion object{

        @Volatile private var instance:ScientistsInventionsDatabase?=null

        operator fun invoke(context:Context)= instance?: synchronized(Any()){

            instance ?: makeDatabase(context).also {
                instance=it
            }
        }

        fun makeDatabase(context: Context)=Room
            .databaseBuilder(context,ScientistsInventionsDatabase::class.java,"ScientistsDatabase")
            .fallbackToDestructiveMigration()
            .build()

    }


}