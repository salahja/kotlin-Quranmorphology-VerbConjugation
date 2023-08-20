package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quranicverbs")
class QuranicVerbsEntity {
    var verb: String? = null
    var roots: String? = null
    var thulathibab: String? = null
    var form = 0
    var chaptername: String? = null
    var frequency = 0
    var meaning: String? = null

    @PrimaryKey
    var id = 0
    fun setRoot(root: String?) {
        roots = root
    }
}