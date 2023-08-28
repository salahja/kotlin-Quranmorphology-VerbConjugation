package sj.hisnul.Dao

import androidx.room.Dao
import androidx.room.Query
import sj.hisnul.entity.hduadetails

@Dao
interface hDuaItemDao {
    @get:Query("SELECT * FROM hduadetails ORDER BY id")
    val duaItemsALL: List<hduadetails?>?

    @Query("SELECT * FROM hduadetails where ID=:aid")
    fun getDitem(aid: String?): List<hduadetails?>?
}