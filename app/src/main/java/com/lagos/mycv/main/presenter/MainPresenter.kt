package com.lagos.mycv.main.presenter

import com.lagos.domain.usecases.GetCurriculumUseCase
import com.lagos.domain.usecases.GetEducationUseCase
import com.lagos.domain.usecases.GetExperienceUseCase
import com.lagos.domain.usecases.GetProfileUseCase
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val view: MainContract.View,
    private val mProfileUseCase: GetProfileUseCase,
    private val mCurriculumUseCase: GetCurriculumUseCase,
    private val mEducationUseCase: GetEducationUseCase,
    private val mExperienceUseCase: GetExperienceUseCase
) : MainContract.Presenter {

    private val mCompositeDisposable = CompositeDisposable()

    override fun getCurriculum() {
        mCompositeDisposable.add(mCurriculumUseCase.execute()
            .doOnSubscribe { view.showProgress() }
            .subscribe({ cvData ->
                view.hideProgress()
                if (cvData == null) {
                    view.setDataError()
                } else {
                    getProfile()
                    getEducationData()
                    getExperienceData()
                }
            }, {
                view.hideProgress()
                view.setDataError()
            })
        )
    }

    override fun getProfile() {
        mCompositeDisposable.add(mProfileUseCase.execute()
            .doOnSubscribe { view.showProgress() }
            .subscribe({ profileData ->
                view.hideProgress()
                if (profileData == null) {
                    view.setDataError()
                } else {
                    view.setData(profileData)
                }
            }, {
                view.hideProgress()
                view.setDataError()
            })
        )
    }

    override fun getEducationData() {
        mCompositeDisposable.add(
            mEducationUseCase.execute()
                .subscribe({ educationData ->
                    if (educationData == null) {
                        view.setDataError()
                    } else {
                        view.setEducationData(educationData)
                    }
                }, {
                    view.setDataError()
                })
        )
    }

    override fun getExperienceData() {
        mCompositeDisposable.add(
            mExperienceUseCase.execute()
                .subscribe({ professionalData ->
                    if (professionalData == null) {
                        view.setDataError()
                    } else {
                        view.setExperienceData(professionalData)
                    }
                }, {
                    view.setDataError()
                })
        )
    }

    override fun dispose() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }
}