package com.lagos.data.mappers

import com.lagos.data.models.ExperienceModelRealm
import com.lagos.domain.mappers.Transform
import com.lagos.domain.models.Experience

class ExperienceMapper : Transform<List<ExperienceModelRealm>, List<Experience>>() {

    override fun transform(value: List<ExperienceModelRealm>): List<Experience> {
        val list: MutableList<Experience> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: ExperienceModelRealm): Experience {
        val name: String = value.name ?: ""
        val dateFrom: String = value.dateFrom ?: ""
        val dateTo: String = value.dateTo ?: ""
        val image: String = value.image ?: ""
        val description: String = value.description ?: ""
        return Experience(name, dateFrom, dateTo, image, description)
    }
}