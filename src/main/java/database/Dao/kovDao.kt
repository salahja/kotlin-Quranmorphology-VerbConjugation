package database.Dao

import androidx.room.Dao
import androidx.room.Query
import database.entity.kov

@Dao
interface kovDao {
    @get:Query(value = "SELECT * FROM kov order by id ")
    val rules: List<kov?>?
}