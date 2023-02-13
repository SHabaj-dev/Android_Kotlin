package com.sbz.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbz.viewmodelexample.databinding.ActivityMainBinding
import com.sbz.viewmodelexample.viewModel.MainViewModel
import com.sbz.viewmodelexample.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, ViewModelFactory(20)).get(MainViewModel ::class.java)
        setText()
        binding.button.setOnClickListener {
            mainViewModel.increment()
            setText()
        }
    }

    fun setText(){
        binding.tvCount.text = mainViewModel.count.toString()
    }
}