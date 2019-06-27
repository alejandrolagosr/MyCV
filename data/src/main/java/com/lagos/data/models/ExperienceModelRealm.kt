package com.lagos.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ExperienceModelRealm() : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name : String? = null
    var dateFrom : String? = null
    var dateTo : String? = null
    var image : String? = null
    var description : String? = null

    constructor(name: String, dateFrom: String, dateTo: String, image: String, description: String) : this() {
        this.name = name
        this.dateFrom = dateFrom
        this.dateTo = dateTo
        this.image = image
        this.description = description
    }
}