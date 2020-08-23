package com.bacl.pimita.moduls.account.presenter

import android.content.Context
import com.bacl.pimita.R
import com.bacl.pimita.base.BasePresenter
import com.bacl.pimita.data.NetworkBuilder
import com.bacl.pimita.extensions.getString
import com.bacl.pimita.model.SignUpRequest
import com.bacl.pimita.model.SignUpResponse
import com.bacl.pimita.moduls.account.view.LoginView
import com.bacl.pimita.utils.AppConstant
import com.bacl.pimita.utils.NetWorkConnection
import com.bacl.pimita.utils.SharePreferenceUtil
import retrofit2.Response

class LoginPresenter(var loginView: LoginView, var context: Context) : BasePresenter(){

    fun signUp(signUpRequest: SignUpRequest?){
        if (!NetWorkConnection.instance!!.isNetworkAvailable()){
            loginView.onErrorNetwork(true)
            return
        }
        loginView.showProgress(true)
        doNetworkRequest(NetworkBuilder.api().signUp(signUpRequest), object : OnNetworkRequest<Response<SignUpResponse>?> {
            override fun onNetworkRequestError(throwable: Throwable?) {
                loginView.signUpFail(throwable?.message ?: R.string.text_error.getString(), -1)
                loginView.showProgress(false)
            }
            override fun onNetworkRequestSuccess(response: Response<SignUpResponse>?) {
                loginView.showProgress(false)
                if (response?.isSuccessful == true){
                    loginView.signUpSuccess()
                }else{
                    loginView.signUpFail(response?.message()?.toString() ?: R.string.text_error.getString(), response?.body()?.status ?: -1)
                }
            }
        })
    }

}