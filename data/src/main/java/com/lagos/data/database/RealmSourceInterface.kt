package com.lagos.data.database

import com.lagos.data.models.EducationModel
import com.lagos.data.models.ExperienceModel
import com.lagos.data.models.ProfileModel
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import io.reactivex.Single

interface RealmSourceInterface {

    fun saveProfile(profileModel: ProfileModel)
    fun saveEducation(educationModel: List<EducationModel>)
    fun saveExperience(experienceModel: List<ExperienceModel>)

    fun getProfile(): Single<Profile>
    fun getEducation(): Single<List<Education>>
    fun getExperience(): Single<List<Experience>>
}