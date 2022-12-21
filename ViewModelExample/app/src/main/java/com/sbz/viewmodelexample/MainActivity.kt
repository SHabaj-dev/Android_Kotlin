package com.sbz.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var buttonCount: Button
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         viewModel = ViewModelProvider(this).get(MainActivityViewModel:: class.java)
         textView = findViewById<TextView>(R.id.tv_count)
         buttonCount = findViewById<Button>(R.id.btn_count)

        textView.text = viewModel.count.toString()

        buttonCount.setOnClickListener {

            viewModel.increaseCount()
            textView.text = viewModel.count.toString()
        }
    }
}