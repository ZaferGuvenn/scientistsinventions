package com.lafimsize.bilimnsanlarvebulular.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsAPI
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsAPIService
import com.lafimsize.bilimnsanlarvebulular.service.ScientistsInventionsDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
class ScientistsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule=InstantTaskExecutorRule()

    lateinit var viewModel: ScientistsViewModel

    @Before
    fun setUp() {

        viewModel= ScientistsViewModel(ApplicationProvider.getApplicationContext())

    }

    @After
    fun tearDown() {

    }

    @Test
    fun `storeScientistsInRoom`(){

        val dao=ScientistsInventionsDatabase(ApplicationProvider.getApplicationContext()).scientistDao()
        var getList= listOf<Scientists>()
        val sentList= arrayListOf<Scientists>()


        val element1=Scientists("Albert Einstein",
            "1879-1955","google.com",
            "Albert Einsteint biography")
        val element2=Scientists("Nikola Tesla",
            "1856-1943","google.com",
            "Nikola Tesla biography")
        val element3=Scientists("Ada Lovelace",
            "1815-1852","google.com",
            "Ada Lovelace biography")
        sentList.addAll(arrayListOf(element1,element2,element3))

        runBlocking {
            dao.deleteScientists()
            dao.insertAllScientists(*listOf(element1,element2,element3).toTypedArray())
            getList=dao.getAllScientists()
        }

        assertThat(getList).isEqualTo(sentList)

    }

    @Test
    fun `deleteAllScientistsInRoom`(){

        val dao=ScientistsInventionsDatabase(ApplicationProvider.getApplicationContext()).scientistDao()
        var getList= listOf<Scientists>()

        runBlocking {
            dao.deleteScientists()
            getList=dao.getAllScientists()
        }

        assertThat(getList).isEmpty()

    }



    @Test
    fun `emptyListBubbleShort`(){

        val list= arrayListOf<Scientists>()



        runBlocking {

            viewModel.bubbleSort(list)

        }

        assertThat(list).isEmpty()

    }

    @Test
    fun `singleElementListBubbleShort`(){

        val list= arrayListOf<Scientists>()
        val list2= arrayListOf<Scientists>()

        val element=Scientists("Albert Einstein",
        "1879-1955","google.com",
            "Albert Einsteint biography")

        list.add(element)
        list2.add(element)

        runBlocking {

            viewModel.bubbleSort(list)

        }

        assertThat(list).isEqualTo(list2)

    }

    @Test
    fun `correctlySortedListBubbleShortTest`(){

        val toBeShortedList= arrayListOf<Scientists>()
        val sortedList= arrayListOf<Scientists>()

        val element1=Scientists("Albert Einstein",
            "1879-1955","google.com",
            "Albert Einsteint biography")
        val element2=Scientists("Nikola Tesla",
        "1856-1943","google.com",
        "Nikola Tesla biography")
        val element3=Scientists("Ada Lovelace",
        "1815-1852","google.com",
        "Ada Lovelace biography")

        toBeShortedList.addAll(arrayListOf(element1,element2,element3))
        sortedList.addAll(arrayListOf(element3,element2,element1))

        runBlocking {

            viewModel.bubbleSort(toBeShortedList)

        }

        assertThat(toBeShortedList).isEqualTo(sortedList)

    }
}