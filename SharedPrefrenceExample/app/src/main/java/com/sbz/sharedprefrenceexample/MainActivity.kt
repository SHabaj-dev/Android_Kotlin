package com.sbz.sharedprefrenceexample

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sbz.sharedprefrenceexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        //Initializing Share Preference variable
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()


    }

    override fun onPause() {
        super.onPause()
        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString().toInt()

        editor.apply {
            putString("sf_name", name)
            putInt("sf_age", age)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name", null)
        val age = sf.getInt("sf_age", 0)

        binding.etName.setText(name)

        binding.etAge.setText(age.toString())
    }
}