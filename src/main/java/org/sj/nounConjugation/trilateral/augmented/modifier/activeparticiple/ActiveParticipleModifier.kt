package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple

import org.sj.nounConjugation.NounLamAlefModifier
import org.sj.nounConjugation.NounSunLamModifier
import org.sj.nounConjugation.trilateral.augmented.modifier.Substituter
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.FormulaApplyingChecker
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.IFormulaApplyingChecker

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: ����� �������� ������ ��� ��� ������
 * ������ �������� �� ������� ������ ������
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
class ActiveParticipleModifier private constructor() {
    private val substituter = Substituter()
    private val geminator = Geminator()
    private val vocalizer = Vocalizer()
    private val mahmouz = Mahmouz()

    // AugmentedTrilateralModifierListener listener //todo
    fun build(
        root:AugmentedTrilateralRoot,
        kov: Int,
        formulaNo: Int,
        conjugations: List<*>,
        listener: Boolean
    ): MazeedConjugationResult {
        val conjResult = MazeedConjugationResult(
            kov, formulaNo,
            root!!, conjugations
        )
        substituter.apply(conjResult)
        geminator.apply(conjResult)
        var applyVocalization = true
        val result: Int = FormulaApplyingChecker.instance.check(root!!, formulaNo)
        if (result == IFormulaApplyingChecker.NOT_VOCALIZED) {
            applyVocalization = false
        } else if (result == IFormulaApplyingChecker.TWO_STATE) {
            applyVocalization = if (listener) //  if (listener == null)
                true else  //asking the listener to apply or not the vocaliztion
            //    applyVocalization = listener.doSelectVocalization();
                true
        }
        if (applyVocalization) {
            vocalizer.apply(conjResult)
        }
        mahmouz.apply(conjResult)
        NounLamAlefModifier.instance.apply(conjResult)
        NounSunLamModifier.instance.apply(conjResult)
        return conjResult
    }

    companion object {
        val instance = ActiveParticipleModifier()
    }
}

