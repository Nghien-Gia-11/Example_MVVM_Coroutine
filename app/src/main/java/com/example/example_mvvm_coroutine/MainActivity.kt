package com.example.example_mvvm_coroutine

import android.os.Bundle
import android.view.View
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

class MainActivity : AppCompatActivity(), OnClick {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: StopWatchViewModel by viewModels()

    private lateinit var stopWatchAdapter: StopWatchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stopWatchAdapter = StopWatchAdapter(emptyList(), this)
        binding.rvStopWatch.apply {
            adapter = stopWatchAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        viewModel.watch.observe(this@MainActivity) {
            stopWatchAdapter.update(it)
        }

        onClickButton(binding.btnStart) {
            viewModel.start(0, true)
            binding.btnStart.visibility = View.INVISIBLE
        }
        onClickButton(binding.btnPause) {
            viewModel.stop(0, true)
            binding.btnContinue.visibility = View.VISIBLE
        }
        onClickButton(binding.btnContinue) {
            viewModel.continues(0, true)
            binding.btnContinue.visibility = View.INVISIBLE
        }
        onClickButton(binding.btnReset) { viewModel.reset(0, true) }

    }

    private fun onClickButton(btn: View, action: () -> Unit) {
        btn.setOnClickListener { action() }
    }

    override fun onClickStart(pos: Int) {
        Toast.makeText(this@MainActivity, "Start", Toast.LENGTH_LONG).show()
        viewModel.start(pos, false)
    }

    override fun onClickPause(pos: Int) {
        Toast.makeText(this@MainActivity, "Pause", Toast.LENGTH_LONG).show()
        viewModel.stop(pos, false)
    }

    override fun onClickContinue(pos: Int) {
        Toast.makeText(this@MainActivity, "Continue", Toast.LENGTH_LONG).show()
        viewModel.continues(pos, false)
    }

    override fun onClickReset(pos: Int) {
        Toast.makeText(this@MainActivity, "Reset", Toast.LENGTH_LONG).show()
        viewModel.reset(pos, false)
    }
}