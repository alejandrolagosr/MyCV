package com.lagos.mycv.injection

import com.lagos.data.database.DatabaseProviderInterface
import com.lagos.data.database.RealmDataSource
import com.lagos.data.database.RealmProvider
import com.lagos.data.database.RealmSourceInterface
import com.lagos.data.job.JobExecutor
import com.lagos.data.job.UIThread
import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.common.ThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun getJobExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    @Singleton
    fun providesPostExecutionThread(): PostExecutionThread {
        return UIThread()
    }

    @Provides
    @Singleton
    fun providesDatabaseProvider(): DatabaseProviderInterface {
        return RealmProvider()
    }

    @Provides
    @Singleton
    fun providesLocalDataSource(realmProvider: DatabaseProviderInterface): RealmSourceInterface {
        return RealmDataSource(realmProvider)
    }
}