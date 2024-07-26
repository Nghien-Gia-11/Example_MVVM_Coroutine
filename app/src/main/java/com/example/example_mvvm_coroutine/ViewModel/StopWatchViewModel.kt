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

    private var _watch = MutableLiveData<List<StopWatch>>(List(5) { StopWatch() })
    val watch: LiveData<List<StopWatch>> get() = _watch

    private var jobs = mutableMapOf<Int, Job?>()

    fun start(index: Int, check: Boolean) {
        val current = _watch.value ?: emptyList()
        if (!check) {
            jobs[index]?.cancel()
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
            for (i in 0..4){
                jobs[i] = null
            }
            jobs.forEach { (_, job) ->
                job?.cancel()
            }
            jobs.forEach { (pos, _) ->
                jobs[pos] = viewModelScope.launch(Dispatchers.Main) {
                    while (true) {
                        delay(1000)
                        current[pos].second++
                        if (current[pos].second == 59) {
                            current[pos].second = 0
                            current[pos].minute += 1
                        }
                        _watch.value = current
                    }
                }
            }
        }
    }

    fun stop(index: Int, check: Boolean) {
        if (!check) {
            jobs[index]?.cancel()
            jobs[index] = null
        } else {
            jobs.forEach { (pos, _) ->
                jobs[pos]?.cancel()
                jobs[pos] = null
            }
        }
    }

    fun continues(index: Int, check: Boolean) {
        start(index, check)
    }

    fun reset(index: Int, check: Boolean) {
        val current = _watch.value ?: emptyList()
        if (!check) {
            jobs[index] = viewModelScope.launch(Dispatchers.Main) {
                current[index].second = 0
                current[index].minute = 0
                _watch.value = current
            }
        } else {
            jobs.forEach { (pos, _) ->
                jobs[pos] = viewModelScope.launch {
                    current[pos].second = 0
                    current[pos].minute = 0
                }
                _watch.value = current
            }
        }
    }

}