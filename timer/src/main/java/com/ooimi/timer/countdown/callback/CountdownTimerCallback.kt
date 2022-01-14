package com.ooimi.timer.countdown.callback

interface CountdownTimerCallback {
    fun onTick(time: Long)
    fun onCancel()
    fun onFinish()
}