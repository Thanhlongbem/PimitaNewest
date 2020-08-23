package com.bacl.pimita.base

import android.content.Context
import android.util.AttributeSet

interface BaseWidget {
    fun init(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
}