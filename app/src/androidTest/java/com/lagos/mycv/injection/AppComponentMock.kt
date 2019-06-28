package com.lagos.mycv.injection

import com.lagos.mycv.main.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModuleMock::class, DataModuleMock::class])
interface AppComponentMock: AppComponent {
    override fun inject(mainActivity: MainActivity)
}