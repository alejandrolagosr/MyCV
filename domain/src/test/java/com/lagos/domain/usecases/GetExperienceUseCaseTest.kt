package com.lagos.domain.usecases

import com.lagos.domain.common.ImmediateThreadExecutor
import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.models.Experience
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetExperienceUseCaseTest {

    @Mock
    private lateinit var mCurriculumRepository: CurriculumRepository

    @Mock
    private lateinit var mPostExecutor: PostExecutionThread

    private lateinit var mEducationUseCase: GetExperienceUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mEducationUseCase = GetExperienceUseCase(mCurriculumRepository, ImmediateThreadExecutor(), mPostExecutor)
    }

    @Test
    fun buildUseCase() {
        val experienceResponse = getEducationResponseTest()
        Mockito.`when`(mCurriculumRepository.getExperience()).thenReturn(Single.just(experienceResponse))
        val receivedResponse = mCurriculumRepository.getExperience().test().assertComplete().values()[0]
        val experience = receivedResponse[0]

        assertEquals(experience.name, experienceResponse.get(0).name)
        assertEquals(experience.description, experienceResponse.get(0).description)
        assertEquals(experience.date_from, experienceResponse.get(0).date_from)
        assertEquals(experience.date_to, experienceResponse.get(0).date_to)
        assertEquals(experience.image, experienceResponse.get(0).image)
    }

    private fun getEducationResponseTest(): List<Experience> {
        val experience = Experience("Company name", "Date from", "Date to",
            "Company image URL", "Work description")
        val experienceList = ArrayList<Experience>()
        experienceList.add(experience)
        return experienceList
    }
}