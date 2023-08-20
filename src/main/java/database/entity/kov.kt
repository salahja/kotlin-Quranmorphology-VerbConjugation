package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kov")
class kov(var c1: String) {
    var c2: String? = null
    var c3: String? = null
    var kov: String? = null
    var rulename: String? = null
    var example: String? = null

    @PrimaryKey(autoGenerate = true)
    var id = 0

}