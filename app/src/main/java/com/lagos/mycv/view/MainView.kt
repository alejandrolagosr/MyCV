package com.lagos.mycv.view

import com.lagos.mycv.models.ProfileModel

interface MainView {
    fun showProgress()

    fun hideProgress()

    fun setData(profile: ProfileModel)

    fun setDataError()
}