package com.bacl.pimita.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.bacl.pimita.extensions.getString
import com.bacl.pimita.R
import kotlinx.android.synthetic.main.base_bottom_bar.view.*
import kotlinx.android.synthetic.main.bottom_bar_item.view.*

class BaseBottomBar : ConstraintLayout, BaseWidget {

    interface OnOptionClickListener {
        fun onHomeClick()
        fun onSearchClick()
        fun onNotificationClick()
        fun onProfileClick()
        fun onMoreClick()
    }
    private var onOptionClickListener: OnOptionClickListener? = null
    private var view: View? = null

    constructor(context: Context?) : super(context) {
        init(context, null, -1)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, -1)
    }

    override fun init(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        view = LayoutInflater.from(context).inflate(R.layout.base_bottom_bar, this, true)
        item_home.icon.setImageResource(R.drawable.icon_home_unselected)
        item_search.icon.setImageResource(R.drawable.ic_search_unselected)
        item_notification.icon.setImageResource(R.drawable.ic_notification_unselected)
        item_profile.icon.setImageResource(R.drawable.ic_profile_unselected)
        item_more.icon.setImageResource(R.drawable.ic_more_unselected)

        item_home.text.text = R.string.text_home.getString()
        item_search.text.text = R.string.text_search.getString()
        item_notification.text.text = R.string.text_notify.getString()
        item_profile.text.text = R.string.text_profile.getString()
        item_more.text.text = R.string.text_more.getString()
        (item_home as MotionLayout).loadLayoutDescription(R.xml.motion_item_tabbar_home)
        (item_search as MotionLayout).loadLayoutDescription(R.xml.motion_item_tabbar_search)
        (item_notification as MotionLayout).loadLayoutDescription(R.xml.motion_item_tabbar_notification)
        (item_profile as MotionLayout).loadLayoutDescription(R.xml.motion_item_tabbar_profile)
        (item_more as MotionLayout).loadLayoutDescription(R.xml.motion_item_tabbar_more)
        item_home.view.setOnClickListener{setSelectedItem(1)}
        item_search.view.setOnClickListener{setSelectedItem(2)}
        item_notification.view.setOnClickListener{setSelectedItem(3)}
        item_profile.view.setOnClickListener{setSelectedItem(4)}
        item_more.view.setOnClickListener { setSelectedItem(5) }
        setSelectedItem(1)
    }

    fun setOnOptionClickListener(onOptionClickListener: OnOptionClickListener?) {
        this.onOptionClickListener = onOptionClickListener
    }

    private fun setSelectedItem(item: Int) {
        when (item) {
            1 -> {
                item_home.view.isEnabled = false
                item_search.view.isEnabled = true
                item_notification.view.isEnabled = true
                item_profile.view.isEnabled = true
                item_more.view.isEnabled = true
                (item_home as MotionLayout).transitionToEnd()
                (item_search as MotionLayout).transitionToStart()
                (item_notification as MotionLayout).transitionToStart()
                (item_profile as MotionLayout).transitionToStart()
                (item_more as MotionLayout).transitionToStart()
                onOptionClickListener?.onHomeClick()
            }
            2 -> {
                item_search.view.isEnabled = false
                item_home.view.isEnabled = true
                item_notification.view.isEnabled = true
                item_profile.view.isEnabled = true
                item_more.view.isEnabled = true
                (item_search as MotionLayout).transitionToEnd()
                (item_home as MotionLayout).transitionToStart()
                (item_notification as MotionLayout).transitionToStart()
                (item_profile as MotionLayout).transitionToStart()
                (item_more as MotionLayout).transitionToStart()
                onOptionClickListener?.onSearchClick()
            }
            3 -> {
                item_notification.view.isEnabled = false
                item_search.view.isEnabled = true
                item_home.view.isEnabled = true
                item_profile.view.isEnabled = true
                item_more.view.isEnabled = true
                (item_notification as MotionLayout).transitionToEnd()
                (item_search as MotionLayout).transitionToStart()
                (item_home as MotionLayout).transitionToStart()
                (item_profile as MotionLayout).transitionToStart()
                (item_more as MotionLayout).transitionToStart()
                onOptionClickListener?.onNotificationClick()
            }
            4 -> {
                item_profile.view.isEnabled = false
                item_search.view.isEnabled = true
                item_home.view.isEnabled = true
                item_notification.view.isEnabled = true
                item_more.view.isEnabled = true
                (item_profile as MotionLayout).transitionToEnd()
                (item_notification as MotionLayout).transitionToStart()
                (item_home as MotionLayout).transitionToStart()
                (item_search as MotionLayout).transitionToStart()
                (item_more as MotionLayout).transitionToStart()
                onOptionClickListener?.onProfileClick()
            }
            5 -> {
                item_more.view.isEnabled = false
                item_search.view.isEnabled = true
                item_home.view.isEnabled = true
                item_notification.view.isEnabled = true
                item_profile.view.isEnabled = true
                (item_more as MotionLayout).transitionToEnd()
                (item_notification as MotionLayout).transitionToStart()
                (item_home as MotionLayout).transitionToStart()
                (item_search as MotionLayout).transitionToStart()
                (item_profile as MotionLayout).transitionToStart()
                onOptionClickListener?.onMoreClick()
            }
        }
    }
}