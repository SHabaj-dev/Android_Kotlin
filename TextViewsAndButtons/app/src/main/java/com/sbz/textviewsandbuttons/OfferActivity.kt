package com.sbz.textviewsandbuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.sbz.textviewsandbuttons.databinding.ActivityMainBinding
import com.sbz.textviewsandbuttons.databinding.ActivityOfferBinding

class OfferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOfferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MYTAG", "SecondActivity: onCreate")
//        setContentView(R.layout.activity_offer)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_offer)

        val userName = intent.getStringExtra("USER_NAME")

        binding.tvOfferPage.text = userName


    }

    override fun onStart() {
        super.onStart()
        Log.i("MYTAG", "SecondActivity: OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MYTAG", "SecondActivity: OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MYTAG", "SecondActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MYTAG", "SecondActivity: onStrop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MYTAG", "SecondActivity: onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MYTAG", "SecondActivity: onRestart")
    }
}