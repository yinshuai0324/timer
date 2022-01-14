package com.ooimi.timer.countdown

import android.os.Handler
import android.os.Looper
import com.ooimi.timer.countdown.callback.CountdownTimerCallback

internal class CountdownTimer(
    private val tag: String,
    private val timer: Long,
    private val interval: Long,
    private val callback: CountdownTimerCallback
) {
    private val handler = Handler(Looper.getMainLooper())
    private var totalTime = timer

    private var status: Int = -1

    private var runnable = Runnable {
        when (status) {
            0 -> {
                //开始
                if (totalTime > 0) {
                    postDelayed(interval)
                    callback.onTick(totalTime)
                } else {
                    removeCallback()
                    callback.onFinish()
                    CountdownManage.removeByTag(tag)
                }
                totalTime -= interval
            }
            2 -> {
                //重新开始
                status = 0
                removeCallback()
                totalTime = timer
                postDelayed(0)
            }
            1 -> {
                //暂停
                removeCallback()
            }
            3 -> {
                //取消
                removeCallback()
                callback.onCancel()
            }
        }

    }

    private fun postDelayed(interval: Long) {
        handler.postDelayed(runnable, interval)
    }

    private fun removeCallback() {
        handler.removeCallbacks(runnable)
    }

    internal fun start() {
        if (status != 0) {
            status = 0
            postDelayed(0)
        }
    }

    internal fun pause() {
        status = 1
        postDelayed(0)
    }

    internal fun restart() {
        status = 2
        postDelayed(0)
    }

    internal fun cancel() {
        status = 3
        postDelayed(0)
    }

}