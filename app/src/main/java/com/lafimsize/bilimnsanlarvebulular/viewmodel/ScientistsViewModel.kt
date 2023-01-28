package com.lafimsize.bilimnsanlarvebulular.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class ScientistsViewModel(application: Application):BaseViewModel(application){


    val mutableScientistsList= MutableLiveData<ArrayList<Scientists>>()
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
                        val tArrayList=ArrayList<Scientists>()
                        tArrayList.addAll(t)

                        bubbleShort(tArrayList)



                        errorDialog.value=false
                        loadingProgress.value=false
                    }

                    override fun onError(e: Throwable) {
                        errorDialog.value=true
                        loadingProgress.value=false

                        e.printStackTrace()
                    }

                })

        )
    }

    private fun bubbleShort(scientistsList:ArrayList<Scientists>){

        launch {
            loadingProgress.value=true

            for (i in 0 until scientistsList.size){
                var temp:Scientists
                for (j in 0 until scientistsList.size-1-i){
                    val firstBirth=scientistsList[j].scientistsBirthDeath.split("-").get(0).toLong()
                    val secondBirth=scientistsList[j+1].scientistsBirthDeath.split("-").get(0).toLong()
                    if (firstBirth>secondBirth){
                        temp=scientistsList[j]
                        scientistsList[j]=scientistsList[j+1]
                        scientistsList[j+1]=temp
                    }
                }
            }
            loadingProgress.value=false
            mutableScientistsList.value=scientistsList

        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}