package com.lagos.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class SkillsModelRealm() : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String? = null

    constructor(name: String) : this() {
        this.name = name
    }
}