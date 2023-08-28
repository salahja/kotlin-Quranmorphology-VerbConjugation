package sj.hisnul.Dao

import androidx.room.Dao
import androidx.room.Query
import sj.hisnul.entity.hduanames

@Dao
interface hDuaNamesDao {
    @Query("select  * from hduanames where chap_id=:cid")
    fun getdualistbychapter(cid: Int): List<hduanames?>?

    @get:Query("select ROWID,* from hduanames group by chap_id")
    val duanames: List<hduanames?>?

    @Query("SELECT * FROM hduanames where category=:id ORDER BY category")
    fun getDuanamesid(id: String?): List<hduanames?>?

    @Query("SELECT * FROM hduanames where category LIKE '%' || :search || '%'")
    fun getDunamesbyCatId(search: String?): List<hduanames?>?

    /*
    WHERE (category == 'search' OR
           category LIKE '%,search' OR
           category LIKE 'search,%' OR
           category LIKE '%,search,%');
     */
    @Query(
        "SELECT * FROM hduanames where category =:search ||" +
                " category LIKE '%,'||:search ||" +
                " category like :search || ',%'"
    )
    fun getDunamesbyCatIdnew(search: String?): List<hduanames?>?

    @Query("SELECT * FROM hduanames where ID=:id ORDER BY category")
    fun getDuanamesByID(id: String?): List<hduanames?>?

    @Query("SELECT * FROM hduanames where fav=:id ORDER BY fav")
    fun getFavdua(id: Int): List<hduanames?>?

    @Query("SELECT * FROM hduanames where fav=:id ORDER BY fav")
    fun getBookmarked(id: Int): List<hduanames?>?

    @Query("SELECT * FROM hduanames where ID=:id ORDER BY fav")
    fun isBookmarked(id: String?): List<hduanames?>?

    @Query(value = "UPDATE hduanames set fav=:fav where chap_id=:id")
    fun updateFav(fav: Int, id: Int): Int
}