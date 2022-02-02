package com.example.daremote

import android.telephony.TelephonyManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(val mobileData: MobileData) : ViewModel() {

//    val mTimeSpan: Int = App.sharedPreferences?.getInt(KEY_TIME_SPAN, 0) ?: 0
//    val mIsLooping: Boolean = App.sharedPreferences?.getBoolean(KEY_LOOP, false) ?: false
//
    private val _mTimeSpan: MutableLiveData<Int>  = MutableLiveData<Int>(0)
    val timeSpan:LiveData<Int> = _mTimeSpan

    private val _mIsLooping: MutableLiveData<Boolean>  = MutableLiveData<Boolean>(false)
    val isLooping:LiveData<Boolean> = _mIsLooping


    private fun startLoop(){
        GlobalScope.launch(Dispatchers.IO) {

//            val imageUrl = URL("‍‍‍")
//
//            val httpConnection = imageUrl.openConnection() as HttpURLConnection
//            httpConnection.doInput = true
//            httpConnection.connect()
//
//            val inputStream = httpConnection.inputStream
//            val bitmapImage = BitmapFactory.decodeStream(inputStream)

            launch(Dispatchers.Main) {
//                imageView.setImageBitmap(bitmapImage)
            }
        }

    }

    private fun stopLoop(){

    }


    fun start() {
        startLoop()
        App.sharedPreferences?.edit()?.run {
            putBoolean(KEY_LOOP, true)
            apply()
        }
    }

    fun stop() {
        stopLoop()
        App.sharedPreferences?.edit()?.run {
            putBoolean(KEY_LOOP, false)
            apply()
        }
    }
}


