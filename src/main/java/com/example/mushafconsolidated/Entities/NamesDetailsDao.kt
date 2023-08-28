package com.example.mushafconsolidated.Entities
import androidx.room.Dao
import androidx.room.Query
import sj.hisnul.entity.AllahNamesDetails

//.AllahNamesDetails


@Dao
open interface NamesDetailsDao {
    @Query(value = "SELECT * FROM namedetails where id=:id")
    fun ALLAH_NAMES_DETAILS_DETAILS(id: Int): List<AllahNamesDetails?>?
}