package com.sbz.viewmodelexample.viewModel

import androidx.lifecycle.ViewModel

class MainViewModel(val initalValue: Int): ViewModel() {
    var count: Int = initalValue;

    fun increment(){
        count++
    }
}