package com.lagos.domain.models

data class Profile (
    val summary : String,
    val education : List<Education>,
    val experience : List<Experience>,
    val skills : List<Skills>,
    val name : String,
    val id : String,
    val image : String
)