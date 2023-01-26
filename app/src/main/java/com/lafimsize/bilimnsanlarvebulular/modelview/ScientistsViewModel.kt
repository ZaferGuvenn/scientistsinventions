package com.lafimsize.bilimnsanlarvebulular.modelview

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ScientistsViewModel(application: Application):BaseViewModel(application){


    val scientistsList= MutableLiveData<ArrayList<Scientists>>()
    val loadingProgress=MutableLiveData<Boolean>()
    val errorDialog=MutableLiveData<Boolean>()

    private val scientistsAPIService=ScientistsAPIService()
    private val disposable=CompositeDisposable()

    fun refreshDataRetrofit(){
        loadingProgress.value=true
        disposable.add(
            scientistsAPIService.getAllScientists()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Scientists>>(){
                    override fun onSuccess(t: List<Scientists>) {
                        val arrayList=ArrayList<Scientists>()
                        arrayList.addAll(t)
                        scientistsList.value=arrayList

                        errorDialog.value=false
                        loadingProgress.value=false
                    }

                    override fun onError(e: Throwable) {
                        errorDialog.value=true
                        loadingProgress.value=false
                        println("******")
                        e.printStackTrace()
                        println("******")
                        println(e.message)
                    }

                })

        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}