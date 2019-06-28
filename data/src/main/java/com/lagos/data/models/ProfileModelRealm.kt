package com.lagos.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ProfileModelRealm() : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var summary: String? = null
    var name: String? = null
    var image: String? = null

    constructor(summary: String, name: String, image: String) : this() {
        this.summary = summary
        this.name = name
        this.image = image
    }
}