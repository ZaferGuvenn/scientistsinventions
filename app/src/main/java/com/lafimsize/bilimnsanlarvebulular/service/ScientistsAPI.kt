package com.lafimsize.bilimnsanlarvebulular.service

import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ScientistsAPI {


    @GET("ZaferGuvenn/KotlinApp5-Scientists/main/scientists.json?token=GHSAT0AAAAAAB35CLUF2SRVBEAEWPMOBCYMY6S53GA")
    fun getScientists():Single<List<Scientists>>

}