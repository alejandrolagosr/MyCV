package com.lagos.mycv.network

import com.lagos.mycv.models.ProfileModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiGistInterface {

    @GET("alejandrolagosr/cc2871f6c0dd8f84d15fc564078e608b/raw/a183e0db539e7421fb2238209d8137fc9f48521b/profile.json")
    fun getProfileData(): Observable<ProfileModel>
}