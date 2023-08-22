package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.TrilateralRoot
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
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
class SpecialEmphsizedImperativeMahmouz2 : SubstitutionsApplier(),
    IUnaugmentedTrilateralModifier {
  

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root!!
        return root!!.c1 == 'ء' && root!!.c2 == 'ك' && root!!.c3 == 'ل' && root!!.conjugation == "1"
    }

    /**
     * override this method to return the custom list
     *
     * @param words List
     * @param root!!  TrilateralRoot
     */
    override fun apply(words:  MutableList<Any>, root: TrilateralRoot?) {
        words.set(2, "كُلَنَّ")
        words.set(3, "كُلِنَّ")
        words.set(4, "كُلاَنِّ")
        words.set(5, "كُلُنَّ")
        words.set(6, "كُلْنَانِّ")
    }

    override val substitutions: List<*>
        get() = TODO("Not yet implemented")


}
