package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "verbcorpus")
class verbcorpus(
    var chapterno: Int,
    var verseno: Int,
    var wordno: Int,
    var token: Int,
    var root_a: String,
    var form: String,
    var thulathibab: String,
    var tag: String,
    var details: String,
    var pOS: String,
    var tense: String,
    var voice: String,
    var lemma_b: String,
    var root_b: String,
    var gendernumber: String,
    var mood_kananumbers: String,
    var kana_mood: String,
    var lemma_a: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)