package com.lagos.domain.usecases

import com.lagos.domain.common.PostExecutionThread
import com.lagos.domain.common.ThreadExecutor
import com.lagos.domain.models.Education
import com.lagos.domain.repository.ProfileRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetEducationUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val executionScheduler: ThreadExecutor,
    private val postExecutionScheduler: PostExecutionThread
) : SingleUseCase<Void, List<Education>>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCase(params: Void?): Single<List<Education>> {
        return profileRepository.getEducation()
            .subscribeOn(Schedulers.from(executionScheduler))
            .observeOn(postExecutionScheduler.getSchedule())
    }

}
