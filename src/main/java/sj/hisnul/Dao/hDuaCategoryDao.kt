package sj.hisnul.Dao

import androidx.room.Dao
import androidx.room.Query
import sj.hisnul.entity.hcategory

@Dao
interface hDuaCategoryDao {
    @Query("SELECT * FROM hcategory ORDER BY id")
    fun getcatetory(): List<hcategory?>?
}