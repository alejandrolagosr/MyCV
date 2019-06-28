package com.lagos.data.mappers

import com.lagos.data.models.ProfileModel
import com.lagos.data.models.ProfileModelRealm
import com.lagos.domain.mappers.Transform

class ProfileToRealmMapper : Transform<ProfileModel?, ProfileModelRealm>() {
    override fun transform(value: ProfileModel?): ProfileModelRealm {
        val name: String = value?.name ?: ""
        val summary: String = value?.summary ?: ""
        val image: String = value?.image ?: ""
        return ProfileModelRealm(name, summary, image)
    }
}