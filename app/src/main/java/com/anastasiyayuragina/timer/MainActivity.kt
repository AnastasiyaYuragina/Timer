package com.anastasiyayuragina.timer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val timer = Timer()
    val stopwatch = Stopwatch()
    var startTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch.onTick = { time ->
            Log.d("111", "Timer: " + time.toString())
            true
        }

        timer.onTick = { time ->
            Log.d("111", time.toString())
            textTimer.text = String.format("%02d:%02d:%02d", time.toTime().hours,
                    time.toTime().minutes, time.toTime().seconds)
            true
        }
        timer.onFinish = { ->
            Log.d("111", "finish")
            textTimer.text = "00:00:00"
            startTime = 0
            playStopButton.setBackgroundResource(R.mipmap.ic_play)
//            stopwatch.start()
        }
//        timer.startWith(10000)

        playStopButton.setOnClickListener {
            if (!timer.isActive && startTime > 0L) {
                timer.isActive = true
                timer.startWith(startTime)
                playStopButton.setBackgroundResource(R.mipmap.ic_stop)
            } else {
                timer.isActive = false
                timer.stop()
                startTime = 0
                playStopButton.setBackgroundResource(R.mipmap.ic_play)
                textTimer.text = "00:00:00"
            }
        }

        plus5secondsButton.setOnClickListener {
            startTime += 5000
            textTimer.text = String.format("%02d:%02d:%02d", startTime.toTime().hours,
                    startTime.toTime().minutes, startTime.toTime().seconds)
        }

        minus5secondsButton.setOnClickListener {
            if (startTime >= 5000) {
                startTime -= 5000
            }
            textTimer.text = String.format("%02d:%02d:%02d", startTime.toTime().hours,
                    startTime.toTime().minutes, startTime.toTime().seconds)
        }

    }
}
