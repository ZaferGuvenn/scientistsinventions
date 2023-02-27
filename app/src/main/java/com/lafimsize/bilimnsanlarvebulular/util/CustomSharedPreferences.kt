package com.lafimsize.bilimnsanlarvebulular.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class CustomSharedPreferences {


    companion object{

        private const val PREFERENCES_TIME="preferences_time"
        private const val PREFERENCES_TIME_INVENTIONS="preferences_time_inventions"

        @Volatile private var instance:CustomSharedPreferences?=null

        private var sharedPreferences:SharedPreferences?=null

        operator fun invoke(context: Context)= instance?: synchronized(Any()){

            instance?:makeSharedPreference(context).also{
                instance=it
            }
        }

        private fun makeSharedPreference(context: Context):CustomSharedPreferences{
            sharedPreferences=context.getSharedPreferences("sPref",MODE_PRIVATE)
            return CustomSharedPreferences()
        }

    }

    fun saveTime(time:Long){
        sharedPreferences?.edit()?.putLong(PREFERENCES_TIME,time)?.commit()
    }

    fun getTime()= sharedPreferences?.getLong(PREFERENCES_TIME,0L)

    fun saveTimeInventions(time:Long){
        sharedPreferences?.edit()?.putLong(PREFERENCES_TIME_INVENTIONS,time)?.commit()
    }

    fun getTimeInventions()= sharedPreferences?.getLong(PREFERENCES_TIME_INVENTIONS,0L)
}