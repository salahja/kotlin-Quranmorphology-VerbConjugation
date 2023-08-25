package org.sj.verbConjugation.trilateral.unaugmented


open class ConjugationResult(
    var kov: Int, root: UnaugmentedTrilateralRoot, //13 conjugated verbs
    var originalResult: List<*>,
) {
    var root: UnaugmentedTrilateralRoot
        protected set

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<*>
        protected set

    init {
        originalResult = originalResult
        this.root = root
        finalResult = ArrayList(originalResult)
    }
}

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


/*
open class ConjugationResult(
    kov: Int,
    root: UnaugmentedTrilateralRoot,
    originalResult:List<*>,
) {
    var kov: Int
    lateinit var root: UnaugmentedTrilateralRoot

    //13 conjugated verbs
    var originalResult: MutableList<Any>
  //  val finalResult: MutableList<Any>
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

 */