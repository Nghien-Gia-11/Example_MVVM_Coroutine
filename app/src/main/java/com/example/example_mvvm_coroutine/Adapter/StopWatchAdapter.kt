package com.example.example_mvvm_coroutine.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_coroutine.Model.StopWatch
import com.example.example_mvvm_coroutine.OnClickRecyclerView.OnClick
import com.example.example_mvvm_coroutine.databinding.LayoutItemWatchBinding

class StopWatchAdapter(private var listWatch: List<StopWatch>, private val onClick: OnClick) :
    RecyclerView.Adapter<StopWatchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: LayoutItemWatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutItemWatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listWatch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtMinute.text = listWatch[position].minute.toString()
        holder.binding.txtSecond.text = listWatch[position].second.toString()
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

    @SuppressLint("NotifyDataSetChanged")
    fun update(newListWatch: List<StopWatch>) {
        listWatch = newListWatch
        notifyDataSetChanged()
    }


}