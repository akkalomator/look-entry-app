package com.akkalomator.libcommon.api

import com.akkalomator.libcommon.items.CodeRequestBody
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LookApi {

    @GET("ping")
    fun ping(): Single<Response<Unit>>

    @POST("code")
    fun sendCode(@Body code: CodeRequestBody): Single<Response<Unit>>
}