package com.lafimsize.bilimnsanlarvebulular.model

import androidx.annotation.Keep
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity
@Keep
class Scientists(

    @SerializedName("scientists_name")
    val scientistsName:String,
    @SerializedName("scientists_birth_death")
    val scientistsBirthDeath:String,
    @SerializedName("scientists_image")
    val scientistsImage:String



)

@Entity
@Keep
class Inventions(

    @SerializedName("inventions_name")
    val inventionName:String,
    @SerializedName("inventions_date")
    val inventionDate:String,
    @SerializedName("inventions_image")
    val inventionImage:String,
    @SerializedName("inventions_description")
    val inventionDescription:String

)