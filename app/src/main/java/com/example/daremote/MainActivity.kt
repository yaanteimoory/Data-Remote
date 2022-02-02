package com.example.daremote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.example.daremote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.isLooping.observe(this, { t->
            if(t) viewModel.start(this) else viewModel.stop()
        })


        binding.switchLoop.setOnCheckedChangeListener { _, isChecked ->
            run {
                Log.d("TAG", "onCreate: OnCheckedChangeListener: in")
                if (isChecked){
                    if (App.sharedPreferences?.getInt(KEY_TIME_SPAN,0) == 0) {
                        Log.d("TAG", "onCreate: OnCheckedChangeListener: time span = 0")
                        binding.switchLoop.isChecked = false
                    } else {
                        Log.d("TAG", "onCreate: OnCheckedChangeListener: on")
                        App.sharedPreferences?.edit()?.putInt(KEY_TIME_SPAN, binding.edittextTimespan.text.toString().toInt())?.apply()
                        Log.d("TAG", "onCreate: OnCheckedChangeListener: ${App.sharedPreferences?.getInt(
                            KEY_TIME_SPAN,0)}")
                        binding.switchLoop.isChecked = true
                        viewModel.mIsLooping.postValue(true)
                    }
                }else{
                    Log.d("TAG", "onCreate: OnCheckedChangeListener: off")
                    binding.switchLoop.isChecked = false
                    viewModel.mIsLooping.postValue(false)
                }
            }
        }
//        binding.edittextTimespan.doAfterTextChanged { text -> App.sharedPreferences?.edit()?.putInt(KEY_TIME_SPAN, text.toString().toInt())?.apply()  }

        binding.edittextTimespan.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                App.sharedPreferences?.edit()?.putInt(KEY_TIME_SPAN, binding.textTimespan.text.toString().toInt())?.apply()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
//        binding.edittextTimespan.setOnEditorActionListener{ view, actionId, event ->
//            run{
//
//                if(actionId == EditorInfo.IME_ACTION_DONE)
//                {
//                    App.sharedPreferences?.edit()?.putInt(KEY_TIME_SPAN, view.text.toString().toInt())?.apply()
//                    return@run true; // consume.
//                }
//                return@run false
//            }
//
//        }


    }
}

