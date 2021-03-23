package com.akkalomator.libcommon.api

import com.akkalomator.libcommon.items.CodeRequestBody
import com.akkalomator.libcommon.items.CodeResponse
import com.akkalomator.libcommon.items.PingResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Api {

    fun ping(): Single<PingResponse> {
        return api.ping()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                if (it.code() == 204)
                    PingResponse(PingResponse.Status.SUCCESS)
                else {
                    PingResponse(PingResponse.Status.ERROR)
                }
            }
    }

    fun sendCode(code: Int): Single<CodeResponse> {
        return api.sendCode(CodeRequestBody(code))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                val sid = it.headers()["Set-Cookie"]
                if (sid == null)
                    CodeResponse.Fail
                else
                    CodeResponse.Success(sid)
            }
    }

    private val api = retrofit.create(LookApi::class.java)

    companion object {

        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

        private const val BASE_URL = "https://development.lookit.hk/api/test/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}