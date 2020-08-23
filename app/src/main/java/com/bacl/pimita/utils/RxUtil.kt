package com.bacl.pimita.utils

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtil {
    private fun RxUtil(){}

    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.computation())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> appleHandlerStartFinish(src: Observable<T?>?): Observable<T> {
        return Observable.using({ null },
                { nothing: Any? -> src }
        ) { resource: Any? -> }
    }
}