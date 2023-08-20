package org.sj.verbConjugation.trilateral.augmented

class MazeedConjugationResult(
    val kov: Int, val formulaNo: Int, val root: AugmentedTrilateralRoot, //13 conjugated verbs
    val originalResult: List<Any?>?
) {

    val finalResult: List<Any>

    init {
        finalResult = listOf(ArrayList(originalResult))
    }
}