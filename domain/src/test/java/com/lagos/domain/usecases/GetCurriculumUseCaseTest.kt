package com.lagos.domain.usecases

import com.lagos.domain.common.ImmediateThreadExecutor
import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.models.Curriculum
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCurriculumUseCaseTest {

    @Mock
    private lateinit var mCurriculumRepository: CurriculumRepository

    @Mock
    private lateinit var mPostExecutor: PostExecutionThread

    private lateinit var mCurriculumUseCase: GetCurriculumUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mCurriculumUseCase = GetCurriculumUseCase(mCurriculumRepository, ImmediateThreadExecutor(), mPostExecutor)
    }

    @Test
    fun buildUseCase() {
        val curriculumResponse = getCurriculumResponseTest()
        Mockito.`when`(mCurriculumRepository.getCurriculum()).thenReturn(Single.just(curriculumResponse))
        val receivedCurriculumResponse = mCurriculumRepository.getCurriculum().test().assertComplete().values()[0]
        val profile = receivedCurriculumResponse.profile
        val education = receivedCurriculumResponse.education.get(0)
        val experience = receivedCurriculumResponse.experience.get(0)

        assertEquals(profile?.name, curriculumResponse.profile?.name)
        assertEquals(profile?.summary, curriculumResponse.profile?.summary)
        assertEquals(profile?.image, curriculumResponse.profile?.image)

        assertEquals(education.name, curriculumResponse.education.get(0).name)
        assertEquals(education.type, curriculumResponse.education.get(0).type)
        assertEquals(education.image, curriculumResponse.education.get(0).image)

        assertEquals(experience.name, curriculumResponse.experience.get(0).name)
        assertEquals(experience.description, curriculumResponse.experience.get(0).description)
        assertEquals(experience.date_from, curriculumResponse.experience.get(0).date_from)
        assertEquals(experience.date_to, curriculumResponse.experience.get(0).date_to)
        assertEquals(experience.image, curriculumResponse.experience.get(0).image)
    }

    private fun getCurriculumResponseTest(): Curriculum {
        val curriculum = Curriculum()
        val profile = Profile("Profile user summary", "User name", "User image URL")
        val education = Education("School name", "School type", "School image URL")
        val experience = Experience("Company name", "Date from", "Date to",
            "Company image URL", "Work description")
        val schoolTrajectoryList = ArrayList<Education>()
        val professionalTrajectoryList = ArrayList<Experience>()
        schoolTrajectoryList.add(education)
        professionalTrajectoryList.add(experience)
        curriculum.profile = profile
        curriculum.experience = professionalTrajectoryList
        curriculum.education = schoolTrajectoryList
        return curriculum
    }
}