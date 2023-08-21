package org.sj.verbConjugation.trilateral.unaugmented.modifier

import HamzaModifier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.VerbLamAlefModifier

/**
 *
 * Title: Sarf Program
 * المعالجة
 *
 * Description: يقوم بفحص واجراء التعديلات المناسبة على الأفعال الثلاثية المجردة
 * بما فيها الاعلال والابدال والهمزة
 * حسب الصيغة ماضي أو مضارع او أمر
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
class UnaugmentedTrilateralModifier private constructor() {
    private val geminator = Geminator()
    private val vocalizer = Vocalizer()
    private val hamzaModifier = HamzaModifier()
    private val postHamzaModifier = PostHamzaModifier()

    /**
     * اخراج قائمة الأفعال بعد التعديلات
     * البدء بالادغام
     *
     * @param root         UnaugmentedTrilateralRoot
     * @param kov          int
     * @param conjugations List
     * @param tense        String (From SystemConstans class the values are stored)  ماضي أو مضارع او أمر
     * @return ConjugationResult
     */
    @JvmOverloads
    fun build(
        root: UnaugmentedTrilateralRoot,
        kov: Int,
        conjugations: List<*>,
        tense: String,
        active: Boolean,
        applyGemination: Boolean = true
    ): ConjugationResult {
        val conjResult = ConjugationResult(kov, root, conjugations as List<Any>)
        if (applyGemination) geminator.apply(tense, active, conjResult)
        vocalizer.apply(tense, active, conjResult)
        hamzaModifier.apply(tense, active, conjResult)
        //خصيصاُ للفعل أثأ
        postHamzaModifier.apply(tense, active, conjResult)
        VerbLamAlefModifier.getInstance().apply(conjResult.finalResult, conjResult)
        return conjResult
    }

    companion object {
        val instance = UnaugmentedTrilateralModifier()
    }
}