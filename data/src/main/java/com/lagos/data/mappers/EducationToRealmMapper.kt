package com.lagos.data.mappers

import com.lagos.data.models.EducationModel
import com.lagos.data.models.EducationModelRealm
import com.lagos.domain.mappers.Transform

class EducationToRealmMapper : Transform<List<EducationModel>, List<EducationModelRealm>>() {

    override fun transform(value: List<EducationModel>): List<EducationModelRealm> {
        val list: MutableList<EducationModelRealm> = ArrayList()
        value.forEach { list.add(transformEducationItem(it)) }
        return list
    }

    private fun transformEducationItem(value: EducationModel): EducationModelRealm {
        val name: String = value.name
        val type: String = value.type
        val image: String = value.image
        return EducationModelRealm(name, type, image)
    }
}