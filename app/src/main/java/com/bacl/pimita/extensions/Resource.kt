package com.bacl.pimita.extensions

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import com.bacl.pimita.App



@ColorInt
fun Int.getColor(): Int {
    return ResourcesCompat.getColor(App.shared().resources, this, null)
}

fun Int.getString(): String{
    return App.shared().getString(this)
}

fun Int.getDrawable(): Drawable? {
    return ResourcesCompat.getDrawable(App.shared().resources, this, null)
}