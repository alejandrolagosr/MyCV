package com.lagos.data.mappers

import com.lagos.data.models.ExperienceModel
import com.lagos.data.models.ExperienceModelRealm
import com.lagos.domain.mappers.Transform

class ExperienceToRealmMapper : Transform<List<ExperienceModel>, List<ExperienceModelRealm>>() {

    override fun transform(value: List<ExperienceModel>): List<ExperienceModelRealm> {
        val list: MutableList<ExperienceModelRealm> = ArrayList()
        value.forEach { list.add(transformExperienceItem(it)) }
        return list
    }

    private fun transformExperienceItem(value: ExperienceModel): ExperienceModelRealm {
        val name: String = value.name
        val dateFrom: String = value.date_from
        val dateTo: String = value.date_to
        val image: String = value.image
        val description: String = value.description
        return ExperienceModelRealm(name, dateFrom, dateTo, image, description)
    }
}
