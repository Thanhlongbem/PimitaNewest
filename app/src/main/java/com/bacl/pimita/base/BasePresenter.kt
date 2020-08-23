package com.bacl.pimita.base

import com.bacl.pimita.utils.RxUtil
import com.bacl.pimita.utils.RxUtil.applySchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response

open class BasePresenter {
    private val mCompositeDisposable = CompositeDisposable()
    fun <T> doNetworkRequest(src: Observable<Response<T>?>?, onNetworkRequest: OnNetworkRequest<Response<T>?>?) {
        mCompositeDisposable.add(RxUtil.appleHandlerStartFinish(src).compose(applySchedulers()).subscribe({ tResponse: Response<T> ->
            onNetworkRequest?.onNetworkRequestSuccess(tResponse)
        }
        ) { throwable: Throwable? -> onNetworkRequest?.onNetworkRequestError(throwable) })
    }

    interface OnNetworkRequest<T> {
        fun onNetworkRequestSuccess(response: T)
        fun onNetworkRequestError(throwable: Throwable?)
    }
}