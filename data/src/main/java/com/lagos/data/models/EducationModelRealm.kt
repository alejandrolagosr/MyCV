package com.lagos.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class EducationModelRealm() : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String? = null
    var type: String? = null
    var image: String? = null

    constructor(name: String, type: String, image: String) : this() {
        this.name = name
        this.type = type
        this.image = image
    }
}