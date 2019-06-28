package com.lagos.domain.usecases

import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.common.ThreadExecutor
import com.lagos.domain.models.Experience
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetExperienceUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository,
    private val executionScheduler: ThreadExecutor,
    private val postExecutionScheduler: PostExecutionThread
) : SingleUseCase<Void, List<Experience>>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCase(params: Void?): Single<List<Experience>> {
        return curriculumRepository.getExperience()
            .subscribeOn(Schedulers.from(executionScheduler))
            .observeOn(postExecutionScheduler.getSchedule())
    }

}