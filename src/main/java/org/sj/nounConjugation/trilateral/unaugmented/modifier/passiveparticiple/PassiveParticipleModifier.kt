package org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple

import org.sj.nounConjugation.NounLamAlefModifier
import org.sj.nounConjugation.NounSunLamModifier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModifier
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: تطبيق المعالجة الخاصة على اسم المفعول
 * ابتداء بالاعلال واخيرا الهمزة
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
class PassiveParticipleModifier private constructor() : IUnaugmentedTrilateralNounModifier {
    private val vocalizer = Vocalizer()
    private val mahmouz = Mahmouz()
    override fun build(
        root: UnaugmentedTrilateralRoot?,
        kov: Int,
        conjugations: List<Any?>?,
        formula: String
    ): ConjugationResult {
        val conjResult = ConjugationResult(kov, root, conjugations, formula)
        vocalizer.apply(conjResult)
        mahmouz.apply(conjResult)
        NounLamAlefModifier.getInstance().apply(conjResult)
        NounSunLamModifier.getInstance().apply(conjResult)
        return conjResult
    }

    companion object {
        val instance = PassiveParticipleModifier()
    }
}