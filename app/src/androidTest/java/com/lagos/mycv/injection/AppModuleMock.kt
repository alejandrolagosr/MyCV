package com.lagos.mycv.injection

import com.lagos.domain.managers.ResourceManagerError
import com.lagos.domain.models.Curriculum
import com.lagos.domain.repository.CurriculumRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModuleMock(private val curriculum: Curriculum) {

    @Provides
    @Singleton
    fun providesRepository(): CurriculumRepository {
        return CVRepositoryMock(curriculum)
    }

    @Provides
    @Singleton
    fun providesResourceManager(): ResourceManagerError {
        return ResourceManagerMock()
    }
}