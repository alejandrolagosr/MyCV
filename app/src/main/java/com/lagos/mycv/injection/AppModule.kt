package com.lagos.mycv.injection

import android.content.Context
import android.content.res.Resources
import com.lagos.data.database.RealmSourceInterface
import com.lagos.data.repository.CurriculumDataRepository
import com.lagos.data.services.ApiInterface
import com.lagos.domain.managers.ResourceManagerError
import com.lagos.domain.repository.CurriculumRepository
import com.lagos.mycv.managers.ResourceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesAppContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun providesResources(context: Context): Resources {
        return context.resources
    }


    @Provides
    @Singleton
    fun providesResumeRepository(cvServices: ApiInterface, realmSource: RealmSourceInterface): CurriculumRepository {
        return CurriculumDataRepository(cvServices, realmSource)
    }

    @Provides
    @Singleton
    fun providesResourceManager(resources: Resources): ResourceManagerError {
        return ResourceManager(resources)
    }

}