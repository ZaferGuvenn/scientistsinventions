package com.lafimsize.bilimnsanlarvebulular.service

import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ScientistsAPIService {


    val api=Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ScientistsAPI::class.java)

    fun getAllScientists():Single<List<Scientists>>{
        return api.getScientists()
    }

    fun getAllInventions():Single<List<Inventions>>{
        return api.getInventions()
    }
}