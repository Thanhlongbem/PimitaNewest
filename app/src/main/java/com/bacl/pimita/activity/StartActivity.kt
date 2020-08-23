package com.bacl.pimita.activity

import android.content.Intent
import com.bacl.pimita.R
import com.bacl.pimita.base.BaseActivity
import com.bacl.pimita.moduls.account.view.LoginActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_start
    }

    override fun initView() {
        btnStart.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }

}