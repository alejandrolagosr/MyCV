package com.lagos.mycv.injection

import com.lagos.domain.models.Curriculum
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single

class CVRepositoryMock(private val curriculum: Curriculum) : CurriculumRepository {

    override fun getCurriculum(): Single<Curriculum> {
        return Single.just(curriculum)
    }

    override fun getEducation(): Single<List<Education>> {
        return Single.just(curriculum.education)
    }

    override fun getExperience(): Single<List<Experience>> {
        return Single.just(curriculum.experience)
    }

    override fun getProfile(): Single<Profile> {
        return Single.just(curriculum.profile)
    }
}