package com.lagos.domain.models

data class Curriculum(
    var profile: Profile? = null,
    var education: List<Education> = ArrayList(),
    var experience: List<Experience> = ArrayList(),
    var skills: String? = null
)