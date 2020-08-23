package com.bacl.pimita.moduls.account.view

interface LoginView{
    fun signUpSuccess()
    fun signUpFail(msg: String, errorCode: Int)
    fun showProgress(show: Boolean)
    fun onErrorNetwork(error: Boolean)
}