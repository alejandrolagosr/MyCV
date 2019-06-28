package com.lagos.domain.usecases

import com.lagos.domain.common.ImmediateThreadExecutor
import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.models.Profile
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetProfileUseCaseTest {

    @Mock
    private lateinit var mCurriculumRepository: CurriculumRepository

    @Mock
    private lateinit var mPostExecutor: PostExecutionThread

    private lateinit var mProfileUseCase: GetProfileUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mProfileUseCase = GetProfileUseCase(mCurriculumRepository, ImmediateThreadExecutor(), mPostExecutor)
    }

    @Test
    fun buildUseCase() {
        val curriculumResponse = getProfileResponseTest()
        Mockito.`when`(mCurriculumRepository.getProfile()).thenReturn(Single.just(curriculumResponse))
        val profile = mCurriculumRepository.getProfile().test().assertComplete().values()[0]
        assertEquals(profile?.name, curriculumResponse.name)
        assertEquals(profile?.summary, curriculumResponse.summary)
        assertEquals(profile?.image, curriculumResponse.image)
    }

    private fun getProfileResponseTest(): Profile {
        return Profile("Profile user summary", "User name", "User image URL")
    }
}