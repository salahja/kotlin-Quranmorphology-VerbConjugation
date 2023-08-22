package org.sj.verbConjugation.trilateral.augmented.modifier

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.FormulaApplyingChecker
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.IFormulaApplyingChecker
import org.sj.verbConjugation.util.VerbLamAlefModifier

class AugmentedTrilateralModifier private constructor() {
    private val substituter = Substituter()
    private val mazeedGeminator = MazeedGeminator()
    private val vocalizerAugmented = VocalizerAugmented()
    private val preVocalizer = PreVocalizer()
    private val sarfHamzaModifier = SarfHamzaModifier()
    fun build(
        root: AugmentedTrilateralRoot,
        kov: Int,
        formulaNo: Int,
        conjugations: List<Any?>?,
        tense: String,
        active: Boolean,
        applyGemination: Boolean,
        listener: Boolean
    ): MazeedConjugationResult {
        val conjResult = MazeedConjugationResult(kov, formulaNo, root!!, conjugations)
        substituter.apply(tense, active, conjResult)
        if (applyGemination) {
            mazeedGeminator.apply(tense, active, conjResult)
        }
        var applyVocalization = true
        val result: Int = FormulaApplyingChecker.Companion.instance.check(root!!, formulaNo)
        if (result == IFormulaApplyingChecker.Companion.NOT_VOCALIZED) {
            applyVocalization = false
        } else if (result == IFormulaApplyingChecker.Companion.TWO_STATE) {
            //asking the listener to apply or not the vocaliztion
            //    applyVocalization = listener.doSelectVocalization();
            applyVocalization = listener
        }
        if (applyVocalization) {
            preVocalizer.apply(tense, active, conjResult)
            vocalizerAugmented.apply(tense, active, conjResult)
        }
        sarfHamzaModifier.apply(tense, active, conjResult)
        VerbLamAlefModifier.instance.apply(conjResult)
        return conjResult
    }

    fun build(
        root: AugmentedTrilateralRoot,
        kov: Int,
        formulaNo: Int,
        conjugations: List<Any?>?,
        tense: String,
        active: Boolean,
        listener: Boolean
    ): MazeedConjugationResult {
        return build(root!!, kov, formulaNo, conjugations, tense, active, true, listener)
    }

    companion object {
        val instance = AugmentedTrilateralModifier()
    }
}