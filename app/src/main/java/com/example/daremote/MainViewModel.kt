package com.example.daremote

import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.time.Duration

class MainViewModel : ViewModel() {

//        val timeSpan: Int = App.sharedPreferences?.getInt(KEY_TIME_SPAN, 0) ?: 0
//    val mIsLooping: Boolean = App.sharedPreferences?.getBoolean(KEY_LOOP, false) ?: false
//
//    private val _mTimeSpan: MutableLiveData<Int> = MutableLiveData<Int>(0)
//    val timeSpan: LiveData<Int> = _mTimeSpan



    val mIsLooping: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLooping: LiveData<Boolean> = mIsLooping
    private var  scope: Job? = null


    private fun startLoop(context: Context) {
        scope = GlobalScope.launch(Dispatchers.IO) {
            while (true) {

                val second = App.sharedPreferences?.getInt(KEY_TIME_SPAN,0)?:0
                Log.d("TAG", "startLoop: sec: $second")
                delay(7000)
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "loop",Toast.LENGTH_LONG).show()
//                imageView.setImageBitmap(bitmapImage)
                }
            }
        }

    }

    private fun stopLoop() {
//            scope?.cancel()
    }


    fun start(context: Context) {
        startLoop(context)
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

class  MobileData(private val context: Context){



    private val service : TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    fun setMobileDataState(mobileDataEnabled: Boolean){
        Toast.makeText(context,"change data",Toast.LENGTH_LONG ).show()
        try {
            service.javaClass.getDeclaredMethod("setDataEnabled", Boolean::class.java).invoke(service,mobileDataEnabled)
        }catch (e: Exception){
            Log.e("TAG", "Error setting mobile data state", e)
        }

    }
    fun getMobileDataState():Boolean{

        try {
            return service.javaClass.getDeclaredMethod("getDataEnabled").invoke(service) as Boolean
        }
        catch (e: Exception){
            Log.e("TAG", "Error getting mobile data state", e)
        }
        return false

    }

}


