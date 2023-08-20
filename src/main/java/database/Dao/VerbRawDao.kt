package database.Dao

import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import database.entity.verbcorpus

@Dao
interface VerbRawDao {
    @RawQuery
    fun getforms(query: SupportSQLiteQuery?): List<verbcorpus?>?
}