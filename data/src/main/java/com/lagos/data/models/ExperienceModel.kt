package com.lagos.data.models

import com.google.gson.annotations.SerializedName

data class ExperienceModel (
    @SerializedName("name") val name : String,
    @SerializedName("date_from") val date_from : String,
    @SerializedName("date_to") val date_to : String,
    @SerializedName("image") val image : String,
    @SerializedName("description") val description : String
)