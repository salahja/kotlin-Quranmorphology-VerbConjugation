package sj.hisnul.Dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sj.hisnul.entity.hcategory

@Dao
interface hDuaCategoryDao {
    @Query("SELECT * FROM hcategory ORDER BY id")
    fun getcatetory(): Flow<List<hcategory>>
}