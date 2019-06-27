package ui.profile

import com.lagos.domain.models.Profile
import com.lagos.mycv.base.BaseView

interface MainView : BaseView {
    fun showProgress()

    fun hideProgress()

    fun setData(profile: Profile)

    fun setDataError()
}