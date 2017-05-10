package com.anastasiyayuragina.timer

import android.os.Handler
import java.util.*

open class Stopwatch : TimerInterface {

    override var isActive: Boolean = false
    override var timeBehind: Long = 0
    override var onTick: ((time: Long) -> (Boolean))? = null

    private val handler: Handler? = Handler()
    protected var startAt : Date? = null

    override fun start() {
        startAt = Date()
        timeBehind = 0
        handler?.post(showTime)
    }

    override fun stop() {
        startAt = null
        handler?.removeCallbacks(showTime)
    }

    var showTime : Runnable = Runnable {
        run {
            if (startAt != null) {
                val currentDate = Date()
                timeBehind = currentDate.time - startAt!!.time
                onTimeUpdated(timeBehind)
                handler?.postDelayed(showTime, 100)
            }
        }
   }

    open fun onTimeUpdated(timeBehind: Long) {
        onTick?.invoke(timeBehind)
    }
}

class  Timer: Stopwatch() {
    private var limit: Long = 0
    var onFinish: ( () -> Unit)? = null

    fun startWith(finishTime: Long) {
        limit = finishTime
        start()
    }

    override fun onTimeUpdated(timeBehind: Long) {
        val timeLeft: Long = limit - timeBehind
        onTick?.invoke(timeLeft)
        if (timeBehind >= limit) {
            stop()
            onFinish?.invoke()
        }
    }
}

