package com.sbz.bmicalculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.sbz.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private var height: String? = null
    private var weight: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main
        )

        binding.btnCalculate.setOnClickListener {
            height = binding.etHeight.text.toString()
            weight = binding.etWeight.text.toString()
            if (height.isNullOrEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    "Height is Empty !!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (weight.isNullOrEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    "Weight is Empty !!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
//                calculateBMI(height!!, weight!!)
                val intent = Intent(this@MainActivity, ResultActivity:: class.java)
                intent.putExtra("HEIGHT", height)
                intent.putExtra("WEIGHT", weight)
                startActivity(intent)
            }


        }
    }

//    private fun calculateBMI(height: String, weight: String) {
//        var bmi = (weight.toFloat() / (height.toFloat() * 100) * (height.toFloat() * 100)).toString()
//         val intent = Intent(this@MainActivity, ResultActivity:: class.java)
//        intent.putExtra("BMI_RESULT", bmi)
//        startActivity(intent)
//    }
}