package com.bacl.pimita.data

import com.bacl.pimita.model.SignUpRequest
import com.bacl.pimita.model.SignUpResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppApi {
    @POST(NetworkEndPoint.POST_SIGN_UP)
    fun signUp(@Body body: SignUpRequest?): Observable<Response<SignUpResponse>?>?
}