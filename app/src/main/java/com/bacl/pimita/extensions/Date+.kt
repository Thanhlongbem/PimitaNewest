@file:Suppress("INACCESSIBLE_TYPE")
package com.bacl.pimita.extensions

import java.util.*
import java.util.Date

fun Date.dateFrom(day: Int, month: Int, year: Int): Date {
    return GregorianCalendar(year, month, day).time
}

fun Date.day(): Int{
    val cal = Calendar.getInstance()
    cal.time = this
    return cal.get(Calendar.DAY_OF_MONTH)
}

fun Date.month(): Int{
    val cal = Calendar.getInstance()
    cal.time = this
    return cal.get(Calendar.MONTH) + 1
}

fun Date.year(): Int{
    val cal = Calendar.getInstance()
    cal.time = this
    return cal.get(Calendar.YEAR)
}

fun Date.toDotString(): String{
    return "${this.day()}.${this.month()}.${this.year()}"
}