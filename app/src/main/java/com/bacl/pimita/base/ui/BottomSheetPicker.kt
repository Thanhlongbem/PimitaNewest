package com.bacl.pimita.base.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bacl.pimita.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.sheet_string_picker.*

class BottomSheetPicker(context: Context) : BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme) {

    init {
        create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.sheet_string_picker)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupView()
    }

    interface shBottomSheetPickerCallback{
    }

    var callback: shBottomSheetPickerCallback? = null
    var onValueChanged: ((String, Int) -> Unit)? = null
    var doneAction: (() -> Unit)? = null
    var data: Array<String>? = null

    private fun setupView(){
        buttonClose.setOnClickListener{
            dismiss()
        }
        numberPicker.setOnValueChangedListener { picker, _, _ ->
            onValueChanged?.let {
                data?.get(picker.value)?.let { it1 -> it(it1, picker.value) }

            }
        }
    }
    override fun dismiss() {
        super.dismiss()
        doneAction?.let {
            it()
        }
    }

    fun showDialog(title: String, data: Array<String>, onValueChanged: ((String, Int) -> Unit), doneAction: (() -> Unit)?){
        this.textTitle.text = title
        this.onValueChanged = onValueChanged
        this.doneAction = doneAction
        this.data = data
        numberPicker.minValue = 0
        numberPicker.maxValue = data.size - 1
        numberPicker.displayedValues = data
        show()
    }
}