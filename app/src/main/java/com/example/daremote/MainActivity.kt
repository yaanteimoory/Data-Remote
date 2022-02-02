package com.example.daremote

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import com.example.daremote.databinding.ActivityMainBinding
import java.lang.Exception
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.isLooping.observe(this, { t->
            if(t) viewModel.start() else viewModel.stop()
        })








    }
}

class  MobileData(private val context: Context){



    private val service : TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    fun setMobileDataState(mobileDataEnabled: Boolean){
        Toast.makeText(context,"change data",Toast.LENGTH_LONG ).show()
        try {
            service.javaClass.getDeclaredMethod("setDataEnabled", Boolean::class.java).invoke(service,mobileDataEnabled)
        }catch (e:Exception){
            Log.e("TAG", "Error setting mobile data state", e)
        }

    }
    fun getMobileDataState():Boolean{

        try {
            return service.javaClass.getDeclaredMethod("getDataEnabled").invoke(service) as Boolean
        }
        catch (e:Exception){
            Log.e("TAG", "Error getting mobile data state", e)
        }
        return false

    }

}