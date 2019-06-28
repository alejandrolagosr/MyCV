package com.lagos.data.database

import io.realm.Realm

class RealmProvider : DatabaseProviderInterface {
    override val instance: Realm
        get() = Realm.getDefaultInstance()
}