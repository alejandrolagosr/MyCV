package com.lagos.domain.repository

import com.lagos.domain.models.Profile
import io.reactivex.Single

interface ProfileRepository {

    fun getProfile(): Single<Profile>

}