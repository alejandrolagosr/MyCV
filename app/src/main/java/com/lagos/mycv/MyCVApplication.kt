package com.lagos.mycv

import androidx.multidex.MultiDexApplication
import com.lagos.data.network.NetworkModule
import com.lagos.mycv.injection.AppComponent
import com.lagos.mycv.injection.AppModule
import com.lagos.mycv.injection.DaggerAppComponent
import com.lagos.mycv.injection.DataModule
import io.realm.Realm

class MyCVApplication : MultiDexApplication() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule())
            .networkModule(NetworkModule())
            .build()
    }
}