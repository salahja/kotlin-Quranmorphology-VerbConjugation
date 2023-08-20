package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer

import java.util.LinkedList

class Formula1ApplyingChecker : IFormulaApplyingChecker() {
   override val twoStateList: MutableList<String> = LinkedList()
   override val notVocalizedList: MutableList<String> = LinkedList()

    init {
        twoStateList.add("جود")
        twoStateList.add("حوش")
        twoStateList.add("حول")
        twoStateList.add("خوص")
        twoStateList.add("خول")
        twoStateList.add("دوء")
        twoStateList.add("روح")
        twoStateList.add("رود")
        twoStateList.add("روض")
        twoStateList.add("سود")
        twoStateList.add("سوغ")
        twoStateList.add("طول")
        twoStateList.add("عور")
        twoStateList.add("لوث")
        twoStateList.add("نوء")
        twoStateList.add("بيض")
        twoStateList.add("حين")
        twoStateList.add("خيف")
        twoStateList.add("خيل")
        twoStateList.add("خيم")
        twoStateList.add("ريف")
        twoStateList.add("عير")
        twoStateList.add("عيل")
        twoStateList.add("عين")
        twoStateList.add("غيب")
        twoStateList.add("غيل")
        twoStateList.add("غيم")
        twoStateList.add("كيس")
        twoStateList.add("ليل")
        twoStateList.add("لين")
        twoStateList.add("نيء")
        twoStateList.add("هيج")
        notVocalizedList.add("حوب")
        notVocalizedList.add("حوج")
        notVocalizedList.add("حوذ")
        notVocalizedList.add("زوج")
        notVocalizedList.add("زوك")
        notVocalizedList.add("عوز")
        notVocalizedList.add("عوص")
        notVocalizedList.add("عوم")
        notVocalizedList.add("نوك")
        notVocalizedList.add("هوج")
        notVocalizedList.add("زين")
        notVocalizedList.add("عيس")
        notVocalizedList.add("عيه")
        notVocalizedList.add("فيق")
        notVocalizedList.add("هيق")
    }

    fun getNotVocalizedList(): List<String> {
        return notVocalizedList
    }

    fun getTwoStateList(): List<String> {
        return twoStateList
    }
}