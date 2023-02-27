package com.lafimsize.bilimnsanlarvebulular.service

import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ScientistsAPI {


    @GET("/ZaferGuvenn/KotlinApp5-Scientists/main/scientists.json")
    fun getScientists():Single<List<Scientists>>

    @GET("/ZaferGuvenn/KotlinApp5-Scientists/main/inventions/inventions-{whichScientist}/inventions.json")
    fun getInventions(@Path("whichScientist") whichScientist:String):Single<List<Inventions>>

}