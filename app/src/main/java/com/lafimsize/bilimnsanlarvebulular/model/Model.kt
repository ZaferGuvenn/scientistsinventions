package com.lafimsize.bilimnsanlarvebulular.model

import androidx.annotation.Keep
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Keep
@Entity
class Scientists(

    @SerializedName("scientists_name")
    val scientistsName:String,
    @SerializedName("scientists_birth_death")
    val scientistsBirthDeath:String,
    @SerializedName("scientists_image")
    val scientistsImage:String



)

class Inventions(

    val inventionName:String,
    val inventionDate:String,
    val inventionImage:String,
    val inventionDescriptions:String

)