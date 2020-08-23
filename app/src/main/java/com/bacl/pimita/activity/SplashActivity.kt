package com.bacl.pimita.activity

import android.content.Intent
import com.bacl.pimita.R
import com.bacl.pimita.base.BaseActivity
import com.github.ybq.android.spinkit.style.ThreeBounce
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.logging.Handler

class SplashActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        val sprite = ThreeBounce()
        spinKit.setIndeterminateDrawable(sprite)
        val handler: android.os.Handler = android.os.Handler()
        handler.postDelayed({
            finish()
            startActivity(Intent(this, StartActivity::class.java))
        }, 2000)
    }
}