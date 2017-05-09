package com.anastasiyayuragina.timer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val timer: TimerInterface = Timer()
    val backTimer = Countdown()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer.onTick = { time ->
            Log.d("111", "Timer: " + time.toString())
            true
        }

        backTimer.onTick = { time ->
            Log.d("111", time.toString())
            true
        }
        backTimer.onFinish = { ->
            Log.d("111", "finish")
            timer.start()
        }
        backTimer.startWith(10000)

    }

}
