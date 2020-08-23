package com.bacl.pimita.base

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.bacl.pimita.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.sheet_network_error.*

abstract class BaseFragment : Fragment() {
    private lateinit var root: View
    private lateinit var progressBarDialog: Dialog
    var sheetNetworkCallbackF: SheetNetworkCallback? = null
    private lateinit var sheetNetwork: BottomSheetDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(getLayoutId(), container, false)
        setupSheetNetwork()
        retainInstance = true
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpProgressBar()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()

    interface SheetNetworkCallback{
        fun onButtonRetryClick()
    }

    //Bottom Sheet Error Network
    private fun setupSheetNetwork(){
        sheetNetwork = BottomSheetDialog(context!!, R.style.AppBottomSheetDialogTheme)
        sheetNetwork.setContentView(R.layout.sheet_network_error)
        sheetNetwork.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sheetNetwork.buttonClose.setOnClickListener{sheetNetwork.dismiss()}
        sheetNetwork.buttonViewSettings.setOnClickListener{ startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))}
        sheetNetwork.buttonRetry.setOnClickListener{
            sheetNetwork.dismiss()
            sheetNetworkCallbackF?.onButtonRetryClick()
        }
    }

    fun showSheetNetwork(show: Boolean){
        if (show) sheetNetwork.show() else sheetNetwork.dismiss()
    }

    private fun setUpProgressBar() {
        progressBarDialog = Dialog(context!!, R.style.FadeDialog)
        progressBarDialog.setContentView(R.layout.dialog_progress)
        progressBarDialog.setCanceledOnTouchOutside(false)
        progressBarDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}