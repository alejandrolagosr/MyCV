package com.lagos.data.mappers

import com.lagos.data.models.CurriculumModel
import com.lagos.data.models.EducationModel
import com.lagos.data.models.ExperienceModel
import com.lagos.data.models.ProfileModel
import com.lagos.domain.mappers.Transform
import com.lagos.domain.models.Curriculum
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile

class CurriculumMapper : Transform<CurriculumModel, Curriculum>() {

    override fun transform(value: CurriculumModel): Curriculum {
        val profile: Profile = transformProfileFromModel(value.profileModel)
        val schoolTrajectory: List<Education> = transformSchoolFromModel(value.educationModel)
        val professionalTrajectory: List<Experience> = transformProfessionalFromModel(value.experienceModel)
        return Curriculum(profile, schoolTrajectory, professionalTrajectory)
    }

    private fun transformProfileFromModel(value: ProfileModel?): Profile {
        val name: String = value?.name ?: ""
        val summary: String = value?.summary ?: ""
        val image: String = value?.image ?: ""
        return Profile(summary, name, image)
    }

    private fun transformSchoolFromModel(value: List<EducationModel>): List<Education> {
        val list: MutableList<Education> = ArrayList()
        value.forEach { list.add(transformSchoolItem(it)) }
        return list
    }

    private fun transformSchoolItem(value: EducationModel): Education {
        val name: String = value.name
        val type: String = value.type
        val image: String = value.image
        return Education(name, type, image)
    }

    private fun transformProfessionalFromModel(value: List<ExperienceModel>): List<Experience> {
        val list: MutableList<Experience> = ArrayList()
        value.forEach { list.add(transformProfessionalItem(it)) }
        return list
    }

    private fun transformProfessionalItem(value: ExperienceModel): Experience {
        val name: String = value.name
        val dateFrom: String = value.date_from
        val dateTo: String = value.date_to
        val image: String = value.image
        val description: String = value.description
        return Experience(name, dateFrom, dateTo, image, description)
    }
}