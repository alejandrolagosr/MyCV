package com.lagos.data.models

import com.google.gson.annotations.SerializedName

data class ProfileModel (
    @SerializedName("summary") val summary : String,
    @SerializedName("education") val education : List<Education>,
    @SerializedName("experience") val experience : List<Experience>,
    @SerializedName("skills") val skills : List<Skills>,
    @SerializedName("name") val name : String,
    @SerializedName("id") val id : String,
    @SerializedName("image") val image : String
)

data class Education (
    @SerializedName("name") val name : String,
    @SerializedName("type") val type : String,
    @SerializedName("image") val image : String
)

data class Experience (
    @SerializedName("name") val name : String,
    @SerializedName("date_from") val date_from : String,
    @SerializedName("date_to") val date_to : String,
    @SerializedName("image") val image : String,
    @SerializedName("description") val description : String
)

data class Skills (
    @SerializedName("name") val name : String
)