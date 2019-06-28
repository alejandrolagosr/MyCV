package com.lagos.data.database

import io.realm.Realm

interface DatabaseProviderInterface {
    val instance: Realm
}