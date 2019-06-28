package com.lagos.data.mappers

import com.lagos.data.models.ExperienceModel
import com.lagos.data.models.ExperienceModelRealm
import com.lagos.domain.mappers.Transform

class ExperienceMapper : Transform<List<ExperienceModelRealm>, List<ExperienceModel>>() {

    override fun transform(value: List<ExperienceModelRealm>): List<ExperienceModel> {
        val list: MutableList<ExperienceModel> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: ExperienceModelRealm): ExperienceModel {
        val name: String = value.name ?: ""
        val dateFrom: String = value.dateFrom ?: ""
        val dateTo: String = value.dateTo ?: ""
        val image: String = value.image ?: ""
        val description: String = value.description ?: ""
        return ExperienceModel(name, dateFrom, dateTo, image, description)
    }
}