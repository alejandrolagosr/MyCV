package com.lagos.mycv.api

import com.lagos.mycv.models.ProfileModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiGistInterface {
    @GET("alejandrolagosr/cc2871f6c0dd8f84d15fc564078e608b/raw/a183e0db539e7421fb2238209d8137fc9f48521b/profile.json")
    fun getProfileData(): Observable<ProfileModel>

    companion object {
        fun create(): ApiGistInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://gist.githubusercontent.com/")
                .build()

            return retrofit.create(ApiGistInterface::class.java)
        }
    }
}