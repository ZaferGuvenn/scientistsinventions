package com.lafimsize.bilimnsanlarvebulular.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Keep
@Entity
data class Scientists(

    @ColumnInfo(name = "scientistsname")
    @SerializedName("scientists_name")
    val scientistsName:String,
    @ColumnInfo(name = "scientists_birth_death")
    @SerializedName("scientists_birth_death")
    val scientistsBirthDeath:String,
    @ColumnInfo(name = "scientists_image")
    @SerializedName("scientists_image")
    val scientistsImage:String



)
{
    @PrimaryKey(autoGenerate = true)
    var uUidScientist:Int=0
}


@Keep
@Entity
data class Inventions(

    @ColumnInfo(name = "inventions_name")
    @SerializedName("inventions_name")
    val inventionName:String,
    @ColumnInfo(name = "inventions_date")
    @SerializedName("inventions_date")
    var inventionDate:String,
    @ColumnInfo(name = "inventions_image")
    @SerializedName("inventions_image")
    val inventionImage:String,
    @ColumnInfo(name = "inventions_description")
    @SerializedName("inventions_description")
    val inventionDescription:String,
    @ColumnInfo(name = "inventions_scientists")
    @SerializedName("inventions_scientists")
    val inventionsScientists:String

):java.io.Serializable{

    @PrimaryKey(autoGenerate = true)
    var uUidInvention:Int=0
}