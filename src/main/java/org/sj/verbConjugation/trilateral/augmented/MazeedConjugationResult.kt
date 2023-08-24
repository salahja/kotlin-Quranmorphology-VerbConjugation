package org.sj.verbConjugation.trilateral.augmented

class MazeedConjugationResult(
    val kov: Int, val formulaNo: Int, val root: AugmentedTrilateralRoot, //13 conjugated verbs
    val originalResult: List<Any?>?
) {

    val finalResult: MutableList<Any>

    init {
        finalResult = listOf(ArrayList(originalResult)).toMutableList()
    }
}


class MazeedConjugationResults(
    val kov: Int, val formulaNo: Int, val root: AugmentedTrilateralRoot, //13 conjugated verbs
    val originalResult: List<*>
) {

    private val finalResult: List<*>

    init {
        finalResult = java.util.ArrayList(originalResult)
    }


}
