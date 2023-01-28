package com.lafimsize.bilimnsanlarvebulular.service

import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ScientistsAPI {


    @GET("/ZaferGuvenn/KotlinApp5-Scientists/main/scientists.json")
    fun getScientists():Single<List<Scientists>>

    @GET("/ZaferGuvenn/KotlinApp5-Scientists/main/inventions/inventionsTesla.json")
    fun getInventions():Single<List<Inventions>>

}