package database.Dao

import androidx.room.Dao
import androidx.room.Query
import database.entity.verbcorpus

@Dao
interface verbcorpusDao {
    @Query(value = "SELECT * FROM verbcorpus where form !=:id")
    fun getmazeedform(id: String?): List<verbcorpus?>?
}