package com.lagos.mycv.injection.component

import com.lagos.mycv.base.BaseView
import com.lagos.mycv.injection.module.ContextModule
import com.lagos.mycv.injection.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import ui.profile.MainPresenter
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param mainPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(mainPresenter: MainPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}