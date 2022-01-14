package com.timer.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.ooimi.timer.TimerManage
import com.ooimi.timer.countdown.callback.CountdownTimerCallback

class MainActivity : AppCompatActivity() {
    private val timer: Long = 10 * 1000
    private lateinit var timeView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timeView = findViewById(R.id.timeView)
    }

    fun onBtnClick(view: View) {
        when (view.id) {
            R.id.startBtn -> {
                TimerManage.getCountdownTimer()
                    .startCountdown("", timer, 1000, object : CountdownTimerCallback {
                        override fun onTick(time: Long) {
                            val timeText = (time / 1000).toString()
                            timeView.text = timeText
                        }

                        override fun onCancel() {
                            timeView.text = "取消"
                        }

                        override fun onFinish() {
                            timeView.text = "已完成"
                        }
                    })
            }
            R.id.pauseBtn -> {
                TimerManage.getCountdownTimer().pauseCountdown()
            }
            R.id.restartBtn -> {
                TimerManage.getCountdownTimer().restartCountdown()
            }
            R.id.cancelBtn -> {
                TimerManage.getCountdownTimer().cancelCountdown()
            }
        }
    }
}