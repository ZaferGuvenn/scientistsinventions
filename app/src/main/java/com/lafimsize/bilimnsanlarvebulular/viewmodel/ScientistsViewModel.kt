package com.lafimsize.bilimnsanlarvebulular.viewmodel

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsAPIService
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsInventionsDatabase
import com.lafimsize.bilimnsanlarvebulular.util.CustomSharedPreferences
import com.lafimsize.bilimnsanlarvebulular.util.IConnectivityObserver
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.distinctUntilChanged

class ScientistsViewModel(application: Application):BaseViewModel(application){


    val mutableScientistsList= MutableLiveData<ArrayList<Scientists>>()
    val loadingProgress=MutableLiveData<Boolean>()
    val errorDialog=MutableLiveData<Boolean>()
    val alertDialog=MutableLiveData<Boolean>()

    private val scientistsAPIService=ScientistsAPIService()
    private val disposable=CompositeDisposable()

    //private val timeNano=10*1000*1000L*1000L//10 saniye
    private val timeNano=7*24*60*60*1000L*1000L*1000L//1 hafta
    private val sharedPreferences=CustomSharedPreferences(getApplication())



    fun getAllData(){

        val storedTime=sharedPreferences.getTime()

        if (storedTime!=0L && storedTime!=null && System.nanoTime()-storedTime<timeNano){
            getDataFromRoom()
        }else{
            refreshDataRetrofit()
        }
    }
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

                        Toast.makeText(getApplication(),"Veriler güncellendi!",Toast.LENGTH_SHORT).show()

                        bubbleShortAndStoreInRoom(tArrayList)

                    }

                    override fun onError(e: Throwable) {
                        errorDialog.value=true
                        loadingProgress.value=false

                        e.printStackTrace()
                    }

                })

        )



    }

    fun getDataFromRoom(){

        loadingProgress.value=true
        launch {
            val dao=ScientistsInventionsDatabase(getApplication()).scientistDao()

            val scientistList=ArrayList<Scientists>()

            if (dao.getAllScientists().isEmpty()){
                Toast.makeText(getApplication(),"Önceden yüklenmiş veri yok!",Toast.LENGTH_SHORT).show()
                alertDialog.value=true
            }else{
                scientistList.addAll(dao.getAllScientists())
                showScientists(scientistList)
                Toast.makeText(getApplication(),"Veriler Roomdan çekildi!",Toast.LENGTH_SHORT).show()
            }


        }
    }


    private fun showScientists(scientistsList:ArrayList<Scientists>){

        mutableScientistsList.value=scientistsList
        alertDialog.value=false
        errorDialog.value=false
        loadingProgress.value=false
    }

    private fun bubbleShortAndStoreInRoom(scientistsList:ArrayList<Scientists>){

        launch {
            loadingProgress.value=true

            for (i in 0 until scientistsList.size){
                var temp:Scientists
                for (j in 0 until scientistsList.size-1-i){
                    val firstBirth= scientistsList[j].scientistsBirthDeath.split("-")[0].toLong()
                    val secondBirth= scientistsList[j+1].scientistsBirthDeath.split("-")[0].toLong()
                    if (firstBirth>secondBirth){
                        temp=scientistsList[j]
                        scientistsList[j]=scientistsList[j+1]
                        scientistsList[j+1]=temp
                    }
                }
            }


            val dao=ScientistsInventionsDatabase(getApplication()).scientistDao()


            dao.deleteScientists()
            val listLong=dao.insertAllScientists(*scientistsList.toTypedArray())

            for(i in listLong.indices){
                scientistsList[i].uUidScientist=listLong[i].toInt()
            }

            sharedPreferences.saveTime(System.nanoTime())

            showScientists(scientistsList)


        }

    }



    override fun onCleared() {
        super.onCleared()

        disposable.clear()

    }


}