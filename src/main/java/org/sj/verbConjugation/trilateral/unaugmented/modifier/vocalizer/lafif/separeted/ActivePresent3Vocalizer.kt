package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
import java.util.LinkedList

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
class ActivePresent3Vocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (يَوْجَى)
        substitutions.add(SuffixSubstitution("َيَ", "َى")) // EX: (لن يَوْجَى)
        substitutions.add(SuffixSubstitution("َيْ", "َ")) // EX: (لم يَوْجَ)
        substitutions.add(InfixSubstitution("َيِي", "َيْ")) // EX: (أنتِ تَوْجَيْنَ)
        substitutions.add(InfixSubstitution("َيُو", "َوْ")) // EX: (أنتم تَوْجَوْنَ)
        substitutions.add(InfixSubstitution("َيُن", "َوُن")) // EX: (أنتم تَوْجَوُنَّ)
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root.conjugation!!.toInt()
        return kov == 30 && noc == 4
    }
}