package org.sj.verbConjugation.trilateral.unaugmented

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: يمثل نتيجة التصريف مع الجذر ونوع الجذر
 * يستعمل في المعالجة بعد التوليد
 *
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
open class ConjugationResult(kov: Int, root: UnaugmentedTrilateralRoot?, originalResult: List<Any>) {
    var kov: Int
    var root: UnaugmentedTrilateralRoot

    //13 conjugated verbs
    var originalResult: List<Any>

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<Any>

    init {
        this.kov = kov
        this.originalResult = originalResult
        this.root = root!!
        finalResult = ArrayList(originalResult)
    }

    fun getFinalResult(): List<Any> {
        return finalResult
    }

    fun getKov(): Int {
        return kov
    }

    fun getRoot(): UnaugmentedTrilateralRoot {
        return root
    }

    override fun toString(): String {
        return "ConjugationResult{" +
                "kov=" + kov +
                ", root=" + root +
                ", originalResult=" + originalResult +
                ", finalResult=" + finalResult +
                '}'
    }
}
