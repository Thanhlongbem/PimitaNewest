package com.bacl.pimita.utils

import android.content.Context

class SharePreferenceUtil {
    companion object {
        public const val SP_NAME = "BACL_SETTING"

        fun getBoolean(context: Context, key: String?, dValue: Boolean): Boolean {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            return preferences.getBoolean(key, dValue)
        }

        fun setBoolean(context: Context, key: String?, value: Boolean) {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getInt(context: Context, key: String?, value: Int): Int {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            return preferences.getInt(key, value)
        }

        fun setInt(context: Context, key: String?, value: Int) {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putInt(key, value)
            editor.apply()
        }

        fun setLong(context: Context, key: String?, value: Long) {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putLong(key, value)
            editor.apply()
        }

        fun getLong(context: Context, key: String?, value: Long): Long {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            return preferences.getLong(key, value)
        }

        fun getString(context: Context, key: String?, value: String?): String? {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            return preferences.getString(key, value)
        }

        fun setString(context: Context, key: String?, value: String?) {
            val preferences = context.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }

}