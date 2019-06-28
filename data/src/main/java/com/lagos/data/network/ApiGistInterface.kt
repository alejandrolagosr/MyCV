package com.lagos.data.network

import com.lagos.data.models.ProfileModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiGistInterface {

    @GET("alejandrolagosr/cc2871f6c0dd8f84d15fc564078e608b/raw/db05c2c2738380a1d822f2fac74f2209752ff1ee/profile.json")
    fun getProfileData(): Observable<ProfileModel>
}