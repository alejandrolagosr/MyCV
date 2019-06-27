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