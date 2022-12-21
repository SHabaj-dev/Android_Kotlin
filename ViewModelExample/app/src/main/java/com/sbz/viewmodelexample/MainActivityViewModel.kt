package com.sbz.viewmodelexample

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    var count = 0

    fun increaseCount(){
        ++count
    }

}