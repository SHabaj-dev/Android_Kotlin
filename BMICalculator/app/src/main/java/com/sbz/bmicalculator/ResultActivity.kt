package com.sbz.bmicalculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.sbz.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
        binding = DataBindingUtil.setContentView(
            this@ResultActivity,
            R.layout.activity_result
        )

        binding.btnRecalculate.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        var height = intent.getStringExtra("HEIGHT")
        var weight = intent.getStringExtra("WEIGHT")

        val weightInFloat = weight.toString().toDouble()
        val heightInFloat = height.toString().toDouble() * 100.00

        val bmi =  weightInFloat / (Math.pow(heightInFloat, 2.0))
        displayResult(bmi.toFloat())
//        var bmi = intent.getStringExtra("BMI_RESULT")
//        bmi = bmi.toString()
////        Log.i("SHABAJ", bmi.toString())
//        val bmiResult = "%.2f".format(bmi).toFloat()
////        Log.i("SHABAJ", bmiResult.toString())
//
//        displayResult(bmiResult)

    }

    private fun displayResult(bmi: Float) {

//        binding.tvBmi.text = bmi.toString()
        var resultText = ""
        var color = 0
        var range = ""
        when {
            bmi < 18.50 -> {
                resultText = "Under Weight"
                color = R.color.underWeight_color
                range = "Your Body Mass is less than 18.50"
            }
            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normalWeight_color
                range = "Your Body Mass is in between 18.50 to 24.99"
            }
            bmi in 25.00..29.99 -> {
                resultText = "Over Weight"
                color = R.color.overWeight_color
                range = "Your Body Mass is in between 25.00 to 29.99"
            }
            else -> {
                resultText = "Obese"
                color = Color.RED
                range = "Your Body Mass is Grater than 29.99"
            }
        }

        binding.tvCategory.text = resultText
        binding.tvBmi.text = bmi.toString()
        binding.tvRangeInfo.text = range


    }
}