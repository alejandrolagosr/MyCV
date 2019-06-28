package com.lagos.domain.usecases

import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.common.ThreadExecutor
import com.lagos.domain.models.Curriculum
import com.lagos.domain.repository.CurriculumRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurriculumUseCase @Inject constructor(
    private val curriculumRepository: CurriculumRepository,
    private val executionScheduler: ThreadExecutor,
    private val postExecutionScheduler: PostExecutionThread
) : SingleUseCase<Void, Curriculum>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCase(params: Void?): Single<Curriculum> {
        return curriculumRepository.getCurriculum()
            .subscribeOn(Schedulers.from(executionScheduler))
            .observeOn(postExecutionScheduler.getSchedule())
    }

}