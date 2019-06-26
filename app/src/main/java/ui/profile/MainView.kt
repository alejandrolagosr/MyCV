package ui.profile

import com.lagos.mycv.base.BaseView
import com.lagos.mycv.models.ProfileModel

interface MainView : BaseView {
    fun showProgress()

    fun hideProgress()

    fun setData(profile: ProfileModel)

    fun setDataError()
}