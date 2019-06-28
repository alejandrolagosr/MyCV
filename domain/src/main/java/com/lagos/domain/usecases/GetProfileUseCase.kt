package com.lagos.domain.usecases

import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.common.ThreadExecutor
import com.lagos.domain.models.Profile
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProfileUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository,
    private val executionScheduler: ThreadExecutor,
    private val postExecutionScheduler: PostExecutionThread
) : SingleUseCase<Void, Profile>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCase(params: Void?): Single<Profile> {
        return curriculumRepository.getProfile()
            .subscribeOn(Schedulers.from(executionScheduler))
            .observeOn(postExecutionScheduler.getSchedule())
    }

    sealed class CatalogException {
        class ConnectionError : Exception()
        class TimeoutException : Exception()
        class GenericError : Exception()
    }
}