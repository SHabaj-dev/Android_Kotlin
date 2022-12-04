package com.sbz.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var rollBtn : Button
    lateinit var diceImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollBtn = findViewById(R.id.btnRoll)
        diceImage = findViewById(R.id.ivDice)
        rollBtn.setOnClickListener {
            generateRandom()
        }


    }

    private fun generateRandom() {
        val selectImage = when(Random().nextInt(6) + 1){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(selectImage)
    }
}