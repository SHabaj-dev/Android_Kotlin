package com.sbz.textviewsandbuttons

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.sbz.textviewsandbuttons.databinding.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MYTAG", "MainActivity: onCreate")
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tvHello.setTextColor(Color.CYAN)

        binding.buttonSubmit.setOnClickListener {

            if (binding.etUserName.text.toString() == "") {
                binding.availOffer.visibility = View.GONE
                binding.tvHello.text = ""
                Toast.makeText(
                    this@MainActivity,
                    "Please Enter The Name",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                userName = "Hello " + binding.etUserName.text.toString()
                binding.tvHello.text = userName
                binding.etUserName.text.clear()
                binding.availOffer.visibility = View.VISIBLE
            }
        }

        binding.availOffer.setOnClickListener {
            val intent = Intent(this@MainActivity, OfferActivity :: class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("MYTAG", "MainActivity: OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MYTAG", "MainActivity: OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MYTAG", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MYTAG", "MainActivity: onStrop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MYTAG", "MainActivity: onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MYTAG", "MainActivity: onRestart")
    }
}