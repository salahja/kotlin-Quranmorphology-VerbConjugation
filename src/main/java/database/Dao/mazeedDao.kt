package database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import database.entity.Mazeed
import database.entity.MujarradVerbs

@Dao
interface mazeedDao {
    @Query(value = "SELECT * FROM mazeed where root=:root")
    fun getMazeedRoot(root: String?): List<Mazeed?>?

    @get:Query(value = "SELECT * FROM mazeed order by root")
    val mazeedAll: List<Mazeed?>?

    @Query(value = "select DISTINCT root,form,babname ,verbtype,kov,kovname,id from mazeed where kov=:kov order by root limit 30")
    fun getMazeedWeakness(kov: String?): List<Mazeed?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: MujarradVerbs?): Long
}