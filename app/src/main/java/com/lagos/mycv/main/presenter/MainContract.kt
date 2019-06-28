package com.lagos.mycv.main.presenter

import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile

interface MainContract {
    interface Presenter {
        fun getCurriculum()
        fun getProfile()
        fun getEducationData()
        fun getExperienceData()
        fun dispose()
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setData(profile: Profile)
        fun setEducationData(education: List<Education>)
        fun setExperienceData(experience: List<Experience>)
        fun setDataError()
    }
}