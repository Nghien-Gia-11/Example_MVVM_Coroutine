package com.example.example_mvvm_coroutine.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StopWatchViewModel : ViewModel(){

    private var _minute = MutableLiveData<Int>()
    private val minute : LiveData<Int> get() = _minute

    private var _second = MutableLiveData<Int>()
    private val second : LiveData<Int> get() = _second



    
}