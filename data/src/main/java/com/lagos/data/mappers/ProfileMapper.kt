package com.lagos.data.mappers

import com.lagos.data.models.ProfileModelRealm
import com.lagos.domain.mappers.Transform
import com.lagos.domain.models.Profile

class ProfileMapper : Transform<ProfileModelRealm, Profile>() {

    override fun transform(value: ProfileModelRealm): Profile {
        val summary: String = value.summary ?: ""
        val name: String = value.name ?: ""
        val image: String = value.image ?: ""
        return Profile(summary, name, image)
    }
}