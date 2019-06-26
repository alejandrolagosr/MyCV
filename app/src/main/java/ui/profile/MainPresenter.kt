package ui.profile

import com.lagos.mycv.base.BasePresenter
import com.lagos.mycv.models.ProfileModel
import com.lagos.mycv.network.ApiGistInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//class MainPresenter(private var mainView: MainView?, private val mainInteractor: MainInteractor) : MainInteractor.OnFinishedListener {
class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {

    @Inject
    lateinit var mApiService: ApiGistInterface

    var disposable: Disposable? = null

    fun getData() {
        view.showProgress()
        disposable = mApiService.getProfileData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: ProfileModel -> onResultSuccess(result) },
                { error -> onResultFail() }
            )

    }

    private fun onResultSuccess(profile: ProfileModel) {
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