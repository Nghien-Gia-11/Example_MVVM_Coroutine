package com.example.example_mvvm_coroutine.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_coroutine.OnClickRecyclerView.OnClick
import com.example.example_mvvm_coroutine.databinding.LayoutItemWatchBinding

class StopWatchAdapter(private val minute : Int, private val second : Int, private val onClick : OnClick) : RecyclerView.Adapter<StopWatchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding : LayoutItemWatchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemWatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtMinute.text = minute.toString()
        holder.binding.txtSecond.text = second.toString()
        holder.binding.btnStart.setOnClickListener {
            onClick.onClickStart(position)
        }
        holder.binding.btnPause.setOnClickListener {
            onClick.onClickPause(position)
        }
        holder.binding.btnContinue.setOnClickListener {
            onClick.onClickContinue(position)
        }
        holder.binding.btnReset.setOnClickListener {
            onClick.onClickReset(position)
        }
    }

}