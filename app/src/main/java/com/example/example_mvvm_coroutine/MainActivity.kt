package com.example.example_mvvm_coroutine

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example_mvvm_coroutine.Adapter.StopWatchAdapter
import com.example.example_mvvm_coroutine.OnClickRecyclerView.OnClick
import com.example.example_mvvm_coroutine.ViewModel.StopWatchViewModel
import com.example.example_mvvm_coroutine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : StopWatchViewModel by viewModels()

    private lateinit var stopWatchAdapter: StopWatchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stopWatchAdapter = StopWatchAdapter(3,20, object : OnClick{
            override fun onClickStart(pos: Int) {
                Toast.makeText(this@MainActivity, "Start", Toast.LENGTH_LONG).show()
            }

            override fun onClickPause(pos: Int) {
                Toast.makeText(this@MainActivity, "Pause", Toast.LENGTH_LONG).show()
            }

            override fun onClickContinue(pos: Int) {
                Toast.makeText(this@MainActivity, "Continue", Toast.LENGTH_LONG).show()
            }

            override fun onClickReset(pos: Int) {
                Toast.makeText(this@MainActivity, "Reset", Toast.LENGTH_LONG).show()
            }

        })

        binding.rvStopWatch.apply {
            adapter = stopWatchAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

    }
}