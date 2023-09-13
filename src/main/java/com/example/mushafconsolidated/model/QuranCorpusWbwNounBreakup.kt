package com.example.mushafconsolidated.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mushafconsolidated.Entities.CorpusEntity
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.wbwentity

data class QuranCorpusWbwNounBreakup(


    @Embedded val quran: QuranEntity,
    @Relation(
        parentColumn = "docid",
        entityColumn = "id"
    )
    val corpus: CorpusEntity,
           @Relation(
     parentColumn = "docid",
     entityColumn = "id"
)
           val noun:NounCorpus,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )

val wbw: wbwentity
)
