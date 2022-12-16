package com.sbz.colorchangingapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sbz.colorchangingapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        val clickAbleViews: List<View> =
            listOf(
                box_one_text, box_two_text, box_three_text, box_four_text,
                box_five_text, constrain_layout, red_button, green_button, blue_button
            )

        for (item in clickAbleViews) {
            item.setOnClickListener {
                makeColored(it)
            }
        }
    }

    private fun makeColored(view: View) {

        when (view.id) {
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)
            R.id.box_three_text -> view.setBackgroundColor(Color.CYAN)
            R.id.box_four_text -> view.setBackgroundColor(Color.LTGRAY)
            R.id.box_five_text -> view.setBackgroundColor(Color.MAGENTA)

            R.id.red_button -> box_three_text.setBackgroundColor(Color.RED)
            R.id.blue_button -> box_four_text.setBackgroundColor(Color.BLUE)
            R.id.green_button -> box_five_text.setBackgroundColor(Color.GREEN)

            else -> view.setBackgroundColor(Color.DKGRAY)
        }

    }

}