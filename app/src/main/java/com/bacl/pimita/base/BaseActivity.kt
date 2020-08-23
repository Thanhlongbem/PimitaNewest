package com.bacl.pimita.base

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bacl.pimita.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import de.hdodenhof.circleimageview.BuildConfig
import kotlinx.android.synthetic.main.sheet_network_error.*


abstract class BaseActivity : AppCompatActivity(), BaseView {
    abstract fun getLayoutId(): Int
    abstract fun initView()

    private lateinit var progressBarDialog: Dialog
    private lateinit var sheetNetwork: BottomSheetDialog

    private var basePresenter: BasePresenter? = null
    var viewGroup: ViewGroup? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setUpProgressBar()
        viewGroup = findViewById(R.id.content)
        initView()
        setupSheetNetwork()
        basePresenter = BasePresenter()
    }

    private fun setUpProgressBar() {
        progressBarDialog = Dialog(this)
        progressBarDialog.setContentView(R.layout.dialog_progress)
        progressBarDialog.setCanceledOnTouchOutside(false)
        progressBarDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun showProgressBar() {
        progressBarDialog.show()
    }

    override fun dismissProgressBar() {
        progressBarDialog.dismiss()
    }

    interface SheetNetworkCallback{
        fun onButtonRetryClick()
    }

    var sheetNetworkCallback: SheetNetworkCallback? = null

    //Bottom Sheet Error Network
    private fun setupSheetNetwork(){
        sheetNetwork = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
        sheetNetwork.setContentView(R.layout.sheet_network_error)
        sheetNetwork.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sheetNetwork.buttonClose.setOnClickListener{sheetNetwork.dismiss()}
        sheetNetwork.buttonViewSettings.setOnClickListener{ startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))}
        sheetNetwork.buttonRetry.setOnClickListener{
            sheetNetworkCallback?.onButtonRetryClick()
            sheetNetwork.dismiss()
        }
    }

    fun showSheetNetwork(show: Boolean){
        if (show) sheetNetwork.show() else sheetNetwork.dismiss()
    }


    fun isReleaseType(): Boolean {
        return !BuildConfig.DEBUG
    }
}