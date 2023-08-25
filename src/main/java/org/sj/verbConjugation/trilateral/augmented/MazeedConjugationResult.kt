package org.sj.verbConjugation.trilateral.augmented


class MazeedConjugationResult {

    val kov: Int
    val formulaNo: Int
    var originalResult: List<*>
    var root: AugmentedTrilateralRoot
        protected set

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<*>
        protected set
    //13 conjugated verbs
    constructor(kov: Int, formulaNo: Int, root: AugmentedTrilateralRoot, originalResult: List<*>) {
        this.kov = kov
        this.formulaNo = formulaNo
        this.originalResult = originalResult

        this.root = root
        finalResult = ArrayList(originalResult)
    }


}