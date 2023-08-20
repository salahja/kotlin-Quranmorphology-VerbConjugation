package com.example.mushafconsolidated.model
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey















@Entity
class wordbyword {
    var word_no: Int
    var chapter_no = 0
    var verse_no = 0
    var root: String? = null
    var translation: String? = null
    var verse_tbl_id = 0

    @PrimaryKey(autoGenerate = true)
    var word_by_word_translationID = 0
    var word: String? = null

    @Ignore
    constructor(
        word_no: Int,
        chapter_no: Int,
        verse_no: Int,
        root: String?,
        translation: String?,
        verse_tbl_id: Int,
        word_by_word_translationID: Int,
        word: String?
    ) {
        this.word_no = word_no
        this.chapter_no = chapter_no
        this.verse_no = verse_no
        this.root = root
        this.translation = translation
        this.verse_tbl_id = verse_tbl_id
        this.word_by_word_translationID = word_by_word_translationID
        this.word = word
    }

    constructor(word_no: Int) {
        this.word_no = word_no
    }
}