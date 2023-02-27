package com.lafimsize.bilimnsanlarvebulular.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsInventionsDatabase
import kotlinx.coroutines.launch

class DescriptionsViewModel(application: Application):BaseViewModel(application) {

    var inventionDesc=MutableLiveData<Inventions>()

    fun getDescFromRoom(id:Long){

        launch {
            val dao=ScientistsInventionsDatabase(getApplication()).scientistDao()
            inventionDesc.value=dao.getInvention(id)
        }

    }
}