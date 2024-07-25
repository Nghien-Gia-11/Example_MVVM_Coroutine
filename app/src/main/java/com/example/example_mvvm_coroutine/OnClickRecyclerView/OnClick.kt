package com.example.example_mvvm_coroutine.OnClickRecyclerView

interface OnClick {
    fun onClickStart(pos : Int)
    fun onClickPause(pos : Int)
    fun onClickContinue(pos : Int)
    fun onClickReset(pos : Int)
}