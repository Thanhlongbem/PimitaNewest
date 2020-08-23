package com.bacl.solution.Extensions

import android.util.Patterns
import java.util.regex.Pattern

fun String.isValidPassword(): Boolean{
    if (this.length < 8) {
        return false
    }
    // Password should contain at least one number
    var exp = ".*[0-9].*"
    var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
    var matcher = pattern.matcher(this)
    if (!matcher.matches()) {
        return false
    }

    // Password should contain at least one capital letter
    exp = ".*[A-Z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(this)
    if (!matcher.matches()) {
        return false
    }

    // Password should contain at least one small letter
    exp = ".*[a-z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(this)
    if (!matcher.matches()) {
        return false
    }

    // Password should contain at least one special character
    // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
    exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(this)
    if (!matcher.matches()) {
        return false
    }
    return true
}

fun String.isValidEmail(): Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPhone(): Boolean{
    return Patterns.PHONE.matcher(this).matches()
}