package com.lagos.domain.managers

//TODO: Implement this
interface ResourceManagerError{
    fun getConnectionErrorMessage(): String
    fun getTimeoutExceptionMessage(): String
    fun getGenericExceptionMessage(): String
    fun getRealmExceptionMessage(): String
}