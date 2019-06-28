package com.lagos.data.mappers

import com.lagos.data.models.ProfileModel
import com.lagos.data.models.ProfileModelRealm
import com.lagos.domain.mappers.Transform

class ProfileMapper : Transform<ProfileModelRealm, ProfileModel>() {

    override fun transform(value: ProfileModelRealm): ProfileModel {
        val name: String = value.name ?: ""
        val summary: String = value.summary ?: ""
        val image: String = value.image ?: ""
        return ProfileModel(name, summary, image)
    }
}