package com.lagos.data.repository

import com.lagos.data.database.RealmSourceInterface
import com.lagos.data.mappers.CurriculumMapper
import com.lagos.data.mappers.EducationMapper
import com.lagos.data.mappers.ExperienceMapper
import com.lagos.data.mappers.ProfileMapper
import com.lagos.data.models.*
import com.lagos.data.services.ApiInterface
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CurriculumDataRepositoryTest {

    @Mock
    private lateinit var realmSource: RealmSourceInterface
    @Mock
    private lateinit var cvServices: ApiInterface

    private lateinit var cvRepository: CurriculumRepository
    private val mapperCV = CurriculumMapper()
    private val mapperProfile = ProfileMapper()
    private val schoolMapper = EducationMapper()
    private val professionalMapper = ExperienceMapper()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        cvRepository = CurriculumDataRepository(cvServices, realmSource)
    }

    @Test
    fun getCurriculum() {
        var curriculum = CurriculumModel()
        val profile = ProfileModel("Profile user summary", "User name", "User image URL")
        val school = EducationModel("School name", "School type", "School image URL")
        val professionalTrajectory = ExperienceModel(
            "Company name", "Date from", "Date to",
            "Company image URL", "Work description"
        )
        val schoolTrajectoryList = ArrayList<EducationModel>()
        val professionalTrajectoryList = ArrayList<ExperienceModel>()
        schoolTrajectoryList.add(school)
        professionalTrajectoryList.add(professionalTrajectory)
        curriculum.profileModel = profile
        curriculum.experienceModel = professionalTrajectoryList
        curriculum.educationModel = schoolTrajectoryList
        Mockito.`when`(cvServices.getCurriculum()).thenReturn(Single.just(curriculum))
        val receivedValues = cvRepository.getCurriculum()
            .test()
            .assertResult(mapperCV.transform(curriculum))
            .values()
        Assert.assertEquals(mapperCV.transform(curriculum), receivedValues[0])
    }

    @Test
    fun getProfile() {
        val profileRealm = ProfileModelRealm("Profile user summary", "User name", "User image URL")
        val profile = Profile("Profile user summary", "User name", "User image URL")
        Mockito.`when`(realmSource.getProfile()).thenReturn(Single.just(profile))
        val receivedValues = cvRepository.getProfile()
            .test()
            .assertResult(mapperProfile.transform(profileRealm))
            .values()
        Assert.assertEquals(profile, receivedValues[0])
    }

    @Test
    fun getEducation() {
        val education = Education("School name", "School type", "School image URL")
        val educationRealm = EducationModelRealm("School name", "School type", "School image URL")
        val educationList = ArrayList<Education>()
        val educationRealmList = ArrayList<EducationModelRealm>()
        educationList.add(education)
        educationRealmList.add(educationRealm)
        Mockito.`when`(realmSource.getEducation()).thenReturn(Single.just(educationList))
        val receivedValues = cvRepository.getEducation()
            .test()
            .assertResult(schoolMapper.transform(educationRealmList))
            .values()
        Assert.assertEquals(educationList, receivedValues[0])
    }

    @Test
    fun getExperience() {
        val professionalTrajectory = Experience(
            "Company name", "Date from", "Date to",
            "Company image URL", "Work description"
        )
        val professionalTrajectoryRealm = ExperienceModelRealm(
            "Company name", "Date from", "Date to",
            "Company image URL", "Work description"
        )
        val professionalTrajectoryList = ArrayList<Experience>()
        val professionalTrajectoryRealList = ArrayList<ExperienceModelRealm>()
        professionalTrajectoryList.add(professionalTrajectory)
        professionalTrajectoryRealList.add(professionalTrajectoryRealm)
        Mockito.`when`(realmSource.getExperience()).thenReturn(Single.just(professionalTrajectoryList))
        val receivedValues = cvRepository.getExperience()
            .test()
            .assertResult(professionalMapper.transform(professionalTrajectoryRealList))
            .values()
        Assert.assertEquals(professionalTrajectoryList, receivedValues[0])
    }
}