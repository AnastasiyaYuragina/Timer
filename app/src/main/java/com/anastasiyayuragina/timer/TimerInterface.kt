package com.anastasiyayuragina.timer

interface TimerInterface {
    val isActive: Boolean
    val timeBehind: Long
    var onTick: ((time: Long) -> (Boolean))?

    fun stop()
    fun start()
}