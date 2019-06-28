package com.lagos.domain.repository

import com.lagos.domain.models.Curriculum
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import io.reactivex.Single

interface CurriculumRepository {

    fun getCurriculum(): Single<Curriculum>
    fun getProfile(): Single<Profile>
    fun getEducation(): Single<List<Education>>
    fun getExperience(): Single<List<Experience>>

}