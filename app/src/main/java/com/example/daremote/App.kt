package com.example.daremote

import android.app.Application
import android.content.SharedPreferences

const val KEY_LOOP = "loop_situation"
const val KEY_TIME_SPAN = "time_span"
const val KEY_SHARED_PREFERENCES = "share_preferences"

class App : Application() {


   companion object{
       private  var mSharedPreferences :SharedPreferences? = null
       val sharedPreferences get() :SharedPreferences? = mSharedPreferences
   }

    override fun onCreate() {
        super.onCreate()
        mSharedPreferences =applicationContext.getSharedPreferences(KEY_SHARED_PREFERENCES,
            MODE_PRIVATE)

    }
}