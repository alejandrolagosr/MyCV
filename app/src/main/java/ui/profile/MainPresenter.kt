package ui.profile

import com.lagos.domain.models.Profile
import com.lagos.domain.usecases.GetProfileUseCase
import com.lagos.mycv.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainPresenter(
    private val mainView: MainView,
    private val getProfileUseCase: GetProfileUseCase
) : BasePresenter<MainView>(mainView) {

    private val compositeDisposable = CompositeDisposable()

    var disposable: Disposable? = null

    fun getData() {
        view.showProgress()
        compositeDisposable.add(getProfileUseCase.execute()
            .subscribe({ profileData ->
                if (profileData == null) {
                    onResultFail()
                } else {
                    onResultSuccess(profileData)
                }
            }, {
                onResultFail()
            }))
    }

    private fun onResultSuccess(profile: Profile) {
        view.hideProgress()
        view.setData(profile)
    }

    private fun onResultFail() {
        view.hideProgress()
        view.setDataError()
    }

    fun onPause() {
        disposable?.dispose()
    }
}