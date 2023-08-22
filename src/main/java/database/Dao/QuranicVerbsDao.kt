package database.Dao

import androidx.room.Dao
import androidx.room.Query
import database.entity.QuranicVerbsEntity

@Dao
interface QuranicVerbsDao {
    @Query(value = "SELECT * FROM quranicverbs where form=0")
    fun getverbsbyForm(): List<QuranicVerbsEntity?>?

    @Query(value = "UPDATE quranicverbs set root=:uroot where id=:id")
    fun updadateRoots(uroot: String?, id: Int): Int

    @Query(value = "UPDATE quranicverbs set thulathibab=:wazan where id=:id")
    fun updadateThulathibab(wazan: String?, id: Int): Int

    @Query(value = "SELECT * from quranicverbs where form!=0")
    fun getverbsMazeed(): List<QuranicVerbsEntity?>? //  @Query("UPDATE orders SET order_price=:price WHERE order_id = :id")
    // void update(Float price, int id);
}