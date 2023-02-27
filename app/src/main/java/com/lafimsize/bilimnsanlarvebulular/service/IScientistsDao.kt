package com.lafimsize.bilimnsanlarvebulular.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.model.Scientists


@Dao
interface IScientistsDao {

    @Query("Select * from Scientists")
    suspend fun getAllScientists():List<Scientists>

    @Query("Select * from Inventions")
    suspend fun getAllInventions():List<Inventions>

    @Query("Select * from Inventions where uUidInvention=:id")
    suspend fun getInvention(id:Long):Inventions


    @Query("Select * from Inventions where inventions_scientists=:whichScientists")
    suspend fun getScientistsInventions(whichScientists:String):List<Inventions>

    @Insert
    suspend fun insertAllScientists(vararg scientists: Scientists):List<Long>


    @Insert
    suspend fun insertAllInventions(vararg inventions: Inventions):List<Long>



    @Query("Delete from Scientists")
    suspend fun deleteScientists()

    @Query("Delete from Inventions")
    suspend fun deleteAllInventions()

    @Query("Delete from Inventions where inventions_scientists=:whichScientists")
    suspend fun deleteInventions(whichScientists: String)


}