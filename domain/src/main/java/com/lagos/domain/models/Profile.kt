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

data class Education (
    val name : String,
    val type : String,
    val image : String
)

data class Experience (
    val name : String,
    val date_from : String,
    val date_to : String,
    val image : String,
    val description : String
)

data class Skills (
    val name : String
)