package database.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "mujarrad")
class MujarradVerbs {
    var verb: String
    var root: String
    var bab: String? = null
    var verbtype: String
    var babname: String
    var kov: String? = null
    var kovname: String? = null

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @Ignore
    constructor(verb: String, root: String, babname: String, verbtype: String) {
        this.verb = verb
        this.root = root
        this.babname = babname
        this.verbtype = verbtype
    }

    constructor(
        verb: String,
        root: String,
        bab: String,
        verbtype: String,
        babname: String,
        kov: String,
        kovname: String,
        id: Int
    ) {
        this.verb = verb
        this.root = root
        this.bab = bab
        this.verbtype = verbtype
        this.babname = babname
        this.kov = kov
        this.kovname = kovname
        this.id = id
    }
}