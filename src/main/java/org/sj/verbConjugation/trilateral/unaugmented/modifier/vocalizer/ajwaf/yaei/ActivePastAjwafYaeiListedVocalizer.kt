package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.AbstractAjwafYaeiListedVocalizer
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:فحص الأجوف حسب قائمة
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
class ActivePastAjwafYaeiListedVocalizer : AbstractAjwafYaeiListedVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("َيِC3ْ", "ِC3ْ")) // EX: ( شِئْتُ)
        substitutions.add(ExpressionInfixSubstitution("َيِC3ّ", "ِC3ّ")) // EX: ( بِتُّ)
        substitutions.add(ExpressionInfixSubstitution("َيِC3َ", "َاC3َ")) // EX: (شاء، بات)
        substitutions.add(ExpressionInfixSubstitution("َيِC3ُ", "َاC3ُ")) // EX: (شاؤوا، باتوا)
    }

    override fun getSubstitutions(): List<*> {
        return substitutions
    }
}