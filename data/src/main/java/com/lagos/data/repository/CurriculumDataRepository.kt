package com.lagos.data.repository

import com.lagos.data.database.RealmSourceInterface
import com.lagos.data.mappers.CurriculumMapper
import com.lagos.data.models.CurriculumModel
import com.lagos.data.models.ProfileModel
import com.lagos.data.services.ApiInterface
import com.lagos.domain.models.Curriculum
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import com.lagos.domain.repository.CurriculumRepository
import com.lagos.domain.usecases.GetProfileUseCase
import io.reactivex.Single
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurriculumDataRepository @Inject constructor(
    val apiGist: ApiInterface,
    val realmSource: RealmSourceInterface
) : CurriculumRepository {

    override fun getCurriculum(): Single<Curriculum> {
        return apiGist.getCurriculum()
            .map {
                insertToRealm(it)
                CurriculumMapper().transform(it)
            }.onErrorResumeNext {
                Single.error(getErrorHandler(it))
            }
    }

    override fun getProfile(): Single<Profile> {
        return realmSource.getProfile()
            .map { it }
            .onErrorResumeNext { Single.error(GetProfileUseCase.CatalogException.GenericError()) }
    }

    override fun getEducation(): Single<List<Education>> {
        return realmSource.getEducation()
            .map { it }
            .onErrorResumeNext { Single.error(GetProfileUseCase.CatalogException.GenericError()) }
    }

    override fun getExperience(): Single<List<Experience>> {
        return realmSource.getExperience()
            .map { it }
            .onErrorResumeNext { Single.error(GetProfileUseCase.CatalogException.GenericError()) }
    }

    private fun insertToRealm(curriculumModel: CurriculumModel) {
        val profileModel = curriculumModel.profileModel ?: ProfileModel()
        val educationModel = curriculumModel.educationModel
        val experienceModel = curriculumModel.experienceModel
        realmSource.saveProfile(profileModel)
        realmSource.saveEducation(educationModel)
        realmSource.saveExperience(experienceModel)
    }

    private fun getErrorHandler(throwable: Throwable): Throwable {
        return when (throwable) {
            is SocketTimeoutException -> GetProfileUseCase.CatalogException.TimeoutException()
            else -> GetProfileUseCase.CatalogException.GenericError()
        }
    }
}