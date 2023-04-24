package com.lafimsize.bilimnsanlarvebulular.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsAPIService
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsInventionsDatabase
import com.lafimsize.bilimnsanlarvebulular.util.CustomSharedPreferences
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class InventionsViewModel(application: Application):BaseViewModel(application) {

    val mutableInventionsList=MutableLiveData<ArrayList<Inventions>>()
    val errorInventionsMsg=MutableLiveData<Boolean>()
    val loadingInventions=MutableLiveData<Boolean>()
    val offlineBtnIsVisible=MutableLiveData<Boolean>()
    //val alertDialog=MutableLiveData<Boolean>()


    private val sharedPreferences2=CustomSharedPreferences(getApplication())

    val inventionsApiService=ScientistsAPIService()
    val disposable=CompositeDisposable()

    //private val nanoTime=7*24*60*60*1000L*1000L*1000L//1 hafta
    //private val nanoTime=0*1000L*1000L*1000L//0 saniye

    fun getAllData(scientistsName: String){
        offlineBtnIsVisible.value=false

        println(scientistsName+"fda")

        launch {
            val dao=ScientistsInventionsDatabase(getApplication())
                .scientistDao().getScientistsInventions(scientistsName)

            if (dao.isEmpty()){
                getInventionsFromRetrofit(scientistsName,false)
            }else{
                getInventionsFromRoom(scientistsName)
            }
        }
    }

    fun getInventionsFromRetrofit(inventionsName:String,forRefressing:Boolean){

        loadingInventions.value=true

        disposable.add(
            inventionsApiService.getAllInventions(inventionsName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableSingleObserver<List<Inventions>>(){
                    override fun onSuccess(t: List<Inventions>) {
                        bubbleShortAndStoreInRoom(t,inventionsName)
                        Toast.makeText(getApplication(),"Veriler güncellendi!",Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                        errorInventionsMsg.value=true
                        loadingInventions.value=false

                        if (forRefressing){
                            offlineBtnIsVisible.value=true
                        }
                        e.printStackTrace()
                    }

                })
        )


    }

    fun getInventionsFromRoom(inventionsName:String){

        loadingInventions.value=true

        launch {

            val dao=ScientistsInventionsDatabase(getApplication()).scientistDao()

            val whichInventions=dao.getScientistsInventions(inventionsName)


            if (whichInventions.isEmpty()){

                Toast.makeText(getApplication(),"Önceden yüklenmiş veri yok!",Toast.LENGTH_SHORT).show()
                errorInventionsMsg.value=true

            }else{
                showInventions(whichInventions)
                Toast.makeText(getApplication(),"Roomdan Çekildi!",Toast.LENGTH_SHORT).show()

            }



        }
    }

    private fun bubbleShortAndStoreInRoom(inventionsList:List<Inventions>,scientistName:String){

        launch {

            runBlocking {
                bubbleShort(inventionsList)
            }

            runBlocking {
                val dao=ScientistsInventionsDatabase(getApplication()).scientistDao()
                dao.deleteInventions(scientistName)
                val listLong=dao.insertAllInventions(*inventionsList.toTypedArray())

                for (i in inventionsList.indices){
                    inventionsList[i].uUidInvention=listLong[i].toInt()
                }

                sharedPreferences2.saveTimeInventions(System.nanoTime())

                showInventions(inventionsList)
            }

        }

    }

    private fun bubbleShort(inventionsList:List<Inventions>){
        loadingInventions.value=true

        for (i in inventionsList.indices){
            for (j in 0 until inventionsList.size-1-i){
                if (inventionsList[j].inventionDate>inventionsList[j+1].inventionDate){
                    val temp=inventionsList[j].inventionDate
                    inventionsList[j].inventionDate=inventionsList[j+1].inventionDate
                    inventionsList[j+1].inventionDate=temp

                }
            }
        }

    }

    private fun showInventions(inventionsList:List<Inventions>){
        loadingInventions.value=false
        errorInventionsMsg.value=false

        val tArrayList= arrayListOf<Inventions>()
        tArrayList.addAll(inventionsList)
        mutableInventionsList.value=tArrayList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}