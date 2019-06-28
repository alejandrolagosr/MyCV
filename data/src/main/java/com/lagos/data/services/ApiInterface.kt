package com.lagos.data.services

import com.lagos.data.models.CurriculumModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET(ENDPOINT)
    fun getCurriculum(): Single<CurriculumModel>

    companion object {
        const val ENDPOINT = "profile.json"
        const val BASE_URL = "https://gist.githubusercontent.com/alejandrolagosr" +
                "/cc2871f6c0dd8f84d15fc564078e608b/raw/1b0f1968728ecc1fddd721bce8d620d5e1c0ba2c/"
    }
}