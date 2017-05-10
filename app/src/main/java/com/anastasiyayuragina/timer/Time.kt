package com.anastasiyayuragina.timer

import java.util.*

data class Time(var hours: Int,
                var minutes: Int,
                var seconds: Int)

fun Long.toTime() : Time {
    val times = GregorianCalendar(TimeZone.getTimeZone("GTM+2"))
    times.time = Date(this)

    val time = Time(times.get(Calendar.HOUR),
            times.get(Calendar.MINUTE),
            times.get(Calendar.SECOND))

    return time
}