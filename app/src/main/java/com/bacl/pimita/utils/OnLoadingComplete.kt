package com.bacl.pimita.utils

import android.graphics.drawable.Drawable

interface OnLoadingComplete {
    fun onClosed(bitmapDrawable: Drawable?)
}