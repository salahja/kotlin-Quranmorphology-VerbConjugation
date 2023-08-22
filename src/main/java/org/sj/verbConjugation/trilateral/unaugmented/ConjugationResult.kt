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
open class ConjugationResult(kov: Int, root: UnaugmentedTrilateralRoot, originalResult: MutableList<*>?) {
    var kov: Int
    lateinit var root: UnaugmentedTrilateralRoot

    //13 conjugated verbs
    var originalResult: MutableList<Any>

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<Any>

    init {
        this.kov = kov
        this.originalResult = originalResult as MutableList<Any>
        this.root = root!!
        finalResult = ArrayList(originalResult)
    }






    override fun toString(): String {
        return "ConjugationResult{" +
                "kov=" + kov +
                ", root!!=" + root!! +
                ", originalResult=" + originalResult +
                ", finalResult=" + finalResult +
                '}'
    }
}
