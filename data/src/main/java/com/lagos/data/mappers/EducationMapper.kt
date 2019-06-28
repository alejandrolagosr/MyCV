package com.lagos.data.mappers

import com.lagos.data.models.EducationModel
import com.lagos.data.models.EducationModelRealm
import com.lagos.domain.mappers.Transform

class EducationMapper : Transform<List<EducationModelRealm>, List<EducationModel>>() {

    override fun transform(value: List<EducationModelRealm>): List<EducationModel> {
        val list: MutableList<EducationModel> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: EducationModelRealm): EducationModel {
        val name: String = value.name ?: ""
        val type: String = value.type ?: ""
        val image: String = value.image ?: ""
        return EducationModel(name, type, image)
    }
}