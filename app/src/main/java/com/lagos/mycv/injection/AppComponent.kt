package com.lagos.mycv.injection

import com.lagos.data.network.NetworkModule
import com.lagos.mycv.main.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}