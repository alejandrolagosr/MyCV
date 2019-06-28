package com.lagos.mycv.injection

import com.lagos.domain.managers.ResourceManagerError

class ResourceManagerMock : ResourceManagerError {
    override fun getConnectionErrorMessage(): String {
        return ""
    }

    override fun getTimeoutExceptionMessage(): String {
        return ""
    }

    override fun getGenericExceptionMessage(): String {
        return ""
    }

    override fun getRealmExceptionMessage(): String {
        return ""
    }
}