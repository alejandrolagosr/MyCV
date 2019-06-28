package com.lagos.domain.usecases

import com.lagos.domain.common.ImmediateThreadExecutor
import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.models.Education
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetEducationUseCaseTest {

    @Mock
    private lateinit var mCurriculumRepository: CurriculumRepository

    @Mock
    private lateinit var mPostExecutor: PostExecutionThread

    private lateinit var mEducationUseCase: GetEducationUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mEducationUseCase = GetEducationUseCase(mCurriculumRepository, ImmediateThreadExecutor(), mPostExecutor)
    }

    @Test
    fun buildUseCase() {
        val educationResponse = getEducationResponseTest()
        Mockito.`when`(mCurriculumRepository.getEducation()).thenReturn(Single.just(educationResponse))
        val receivedResponse = mCurriculumRepository.getEducation().test().assertComplete().values()[0]
        val education = receivedResponse[0]
        assertEquals(education.name, educationResponse[0].name)
        assertEquals(education.type, educationResponse[0].type)
        assertEquals(education.image, educationResponse[0].image)
    }

    private fun getEducationResponseTest(): List<Education> {
        val education = Education("School name", "School type", "School image URL")
        val educationList = ArrayList<Education>()
        educationList.add(education)
        return educationList
    }
}