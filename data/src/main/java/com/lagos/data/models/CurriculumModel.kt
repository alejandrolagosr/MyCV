package com.lagos.data.models

data class CurriculumModel(
    var profileModel: ProfileModel? = null,
    var educationModel: List<EducationModel> = ArrayList(),
    var experienceModel: List<ExperienceModel> = ArrayList()
)