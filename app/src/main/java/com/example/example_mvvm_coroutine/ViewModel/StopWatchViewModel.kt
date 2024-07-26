package com.example.example_mvvm_coroutine.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_mvvm_coroutine.Model.StopWatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopWatchViewModel : ViewModel() {

    private var _watch = MutableLiveData<List<StopWatch>>(List(5){StopWatch()})
    val watch: LiveData<List<StopWatch>> get() = _watch

    private val jobs = mutableMapOf<Int, Job?>()

    fun start(index: Int) {
        val current = _watch.value ?: emptyList()
        if (jobs[index] == null) {
            jobs[index] = viewModelScope.launch(Dispatchers.Main) {
                while (true) {
                    delay(1000)
                    current[index].second++
                    if (current[index].second == 59) {
                        current[index].minute += 1
                        current[index].second = 0
                    }
                    _watch.value = current
                }
            }
        } else {
            jobs[index]?.cancel()
            jobs[index] = null
        }
    }

    fun stop(index: Int){
        val current = _watch.value ?: emptyList()
        if (jobs[index] == null) {
            jobs[index] = viewModelScope.launch(Dispatchers.Main) {
                while (true) {
                    delay(1000)
                    current[index].second++
                    if (current[index].second == 59) {
                        current[index].minute += 1
                        current[index].second = 0
                    }
                    _watch.value = current
                }
            }
        } else {
            jobs[index]?.cancel()
            jobs[index] = null
        }
    }




}