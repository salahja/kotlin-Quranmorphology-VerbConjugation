package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractFaaMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class FaaMahmouz : AbstractFaaMahmouz() {
    override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ءَا", "آ")) // EX: (آكِلٌ)
    }

    override fun getSubstitutions(): List<*> {
        return substitutions
    }
}