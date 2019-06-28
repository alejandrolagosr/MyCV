package com.lagos.mycv.managers

import android.content.res.Resources
import com.lagos.domain.managers.ResourceManagerError
import com.lagos.mycv.R

//TODO: Add correct error messages
class ResourceManager(private val resources: Resources) : ResourceManagerError {
    override fun getRealmExceptionMessage(): String {
        return resources.getString(R.string.load_error)
    }

    override fun getConnectionErrorMessage(): String {
        return resources.getString(R.string.load_error)
    }

    override fun getTimeoutExceptionMessage(): String {
        return resources.getString(R.string.load_error)
    }

    override fun getGenericExceptionMessage(): String {
        return resources.getString(R.string.load_error)
    }
}