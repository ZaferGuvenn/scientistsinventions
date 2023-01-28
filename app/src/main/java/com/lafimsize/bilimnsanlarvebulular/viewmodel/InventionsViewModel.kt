package com.lafimsize.bilimnsanlarvebulular.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class InventionsViewModel(application: Application):BaseViewModel(application) {

    val mutableInventionsList=MutableLiveData<ArrayList<Inventions>>()
    val errorInventionsMsg=MutableLiveData<Boolean>()
    val loadingInventions=MutableLiveData<Boolean>()

    val inventionsApiService=ScientistsAPIService()
    val disposable=CompositeDisposable()

    fun getInventionsFromRetrofit(){

        loadingInventions.value=true

        disposable.add(
            inventionsApiService.getAllInventions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableSingleObserver<List<Inventions>>(){
                    override fun onSuccess(t: List<Inventions>) {
                        loadingInventions.value=false
                        errorInventionsMsg.value=false

                        val tArrayList= arrayListOf<Inventions>()
                        tArrayList.addAll(t)
                        mutableInventionsList.value=tArrayList

                    }

                    override fun onError(e: Throwable) {
                        errorInventionsMsg.value=true
                        loadingInventions.value=false
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}