package com.lagos.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ProfileModelRealm() : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String? = null
    var image: String? = null
    var summary: String? = null

    constructor(name: String, image: String, summary: String) : this() {
        this.name = name
        this.image = image
        this.summary = summary
    }
}