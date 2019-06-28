package com.lagos.data.mappers

import com.lagos.data.models.EducationModelRealm
import com.lagos.domain.mappers.Transform
import com.lagos.domain.models.Education

class EducationMapper : Transform<List<EducationModelRealm>, List<Education>>() {

    override fun transform(value: List<EducationModelRealm>): List<Education> {
        val list: MutableList<Education> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: EducationModelRealm): Education {
        val name: String = value.name ?: ""
        val type: String = value.type ?: ""
        val image: String = value.image ?: ""
        return Education(name, type, image)
    }
}