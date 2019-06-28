package com.lagos.data.database

import com.lagos.data.mappers.*
import com.lagos.data.models.*
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import com.lagos.domain.usecases.GetProfileUseCase
import io.reactivex.Single

class RealmDataSource(private val databaseProvider: DatabaseProviderInterface) : RealmSourceInterface {

    override fun saveProfile(profileModel: ProfileModel) {
        databaseProvider.instance.use { realm ->
            realm.executeTransaction {
                val profileRealm: ProfileModelRealm = ProfileToRealmMapper().transform(profileModel)
                it.deleteAll()
                it.copyToRealmOrUpdate(profileRealm)
            }
        }
    }

    override fun saveEducation(educationModel: List<EducationModel>) {
        databaseProvider.instance.use { realm ->
            realm.executeTransaction {
                val schoolRealm: List<EducationModelRealm> = EducationToRealmMapper().transform(educationModel)
                it.copyToRealmOrUpdate(schoolRealm)
            }
        }
    }

    override fun saveExperience(experienceModel: List<ExperienceModel>) {
        databaseProvider.instance.use { realm ->
            realm.executeTransaction {
                val professionalRealm: List<ExperienceModelRealm> = ExperienceToRealmMapper().transform(experienceModel)
                it.copyToRealmOrUpdate(professionalRealm)
            }
        }
    }

    override fun getProfile(): Single<Profile> {
        return Single.fromCallable {
            return@fromCallable databaseProvider.instance.use {
                val realmResults = it.where(ProfileModelRealm::class.java).findFirst()
                if (realmResults == null) {
                    throw GetProfileUseCase.CatalogException.GenericError()
                } else {
                    ProfileMapper().transform(realmResults)
                }
            }
        }
    }

    override fun getEducation(): Single<List<Education>> {
        return Single.fromCallable {
            return@fromCallable databaseProvider.instance.use {
                val realmResults = it.where(EducationModelRealm::class.java).findAll()
                if (realmResults == null) {
                    throw GetProfileUseCase.CatalogException.GenericError()
                } else {
                    EducationMapper().transform(realmResults)
                }
            }
        }
    }

    override fun getExperience(): Single<List<Experience>> {
        return Single.fromCallable {
            return@fromCallable databaseProvider.instance.use {
                val realmResults = it.where(ExperienceModelRealm::class.java).findAll()
                if (realmResults == null) {
                    throw GetProfileUseCase.CatalogException.GenericError()
                } else {
                    ExperienceMapper().transform(realmResults)
                }
            }
        }
    }
}