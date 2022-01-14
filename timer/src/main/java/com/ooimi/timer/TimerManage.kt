package com.ooimi.timer

import com.ooimi.timer.countdown.CountdownManage

class TimerManage {
    companion object {
        fun getCountdownTimer(): CountdownManage {
            return CountdownManage
        }
    }
}