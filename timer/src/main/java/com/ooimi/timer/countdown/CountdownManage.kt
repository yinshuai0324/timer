package com.ooimi.timer.countdown

import android.os.CountDownTimer
import android.text.TextUtils
import com.ooimi.timer.countdown.callback.CountdownTimerCallback

object CountdownManage {
    private val DEFAULT_KEY = "KEY_COUNTDOWN"
    private val timer = HashMap<String, CountdownTimer>()

    /**
     * 开始倒计时
     */
    fun startCountdown(
        tag: String? = "",
        totalTime: Long,
        interval: Long,
        callback: CountdownTimerCallback
    ) {
        val key = if (!TextUtils.isEmpty(tag)) {
            tag
        } else {
            DEFAULT_KEY
        }
        if (!timer.containsKey(key)) {
            timer[key ?: DEFAULT_KEY] =
                CountdownTimer(key ?: DEFAULT_KEY, totalTime, interval, callback)
        }
        getTimerByTag(key)?.start()
    }

    /**
     * 暂停倒计时
     */
    fun pauseCountdown(tag: String? = "") {
        getTimerByTag(tag)?.pause()
    }

    /**
     * 重新倒计时
     */
    fun restartCountdown(tag: String? = "") {
        getTimerByTag(tag)?.restart()
    }

    /**
     * 取消倒计时
     */
    fun cancelCountdown(tag: String? = "") {
        getTimerByTag(tag)?.cancel()
        removeByTag(tag)
    }

    /**
     * 移除Timer
     */
    internal fun removeByTag(tag: String?) {
        val key = if (!TextUtils.isEmpty(tag)) tag else DEFAULT_KEY
        if (timer.containsKey(key)) {
            timer.remove(key)
        }
    }


    /**
     * 根据Key 获取Timer
     */
    private fun getTimerByTag(tag: String?): CountdownTimer? {
        val key = if (!TextUtils.isEmpty(tag)) {
            tag
        } else {
            DEFAULT_KEY
        }
        if (timer.containsKey(key)) {
            return timer[key]
        }
        return null
    }
}