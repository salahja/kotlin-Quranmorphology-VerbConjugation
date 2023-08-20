package org.sj.verbConjugation.trilateral.augmented.passive.present

import org.sj.verbConjugation.util.PresentConjugationDataContainer

class AugmentedPassivePresentConjugator private constructor() {
    val nominativeConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.getInstance().nominativeLastDprList,
        PresentConjugationDataContainer.getInstance().nominativeConnectedPronounList
    )
    val accusativeConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.getInstance().accusativeLastDprList,
        PresentConjugationDataContainer.getInstance().accusativeConnectedPronounList
    )
    val jussiveConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.getInstance().jussiveLastDprList,
        PresentConjugationDataContainer.getInstance().jussiveConnectedPronounList
    )
    val emphasizedConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.getInstance().emphasizedLastDprList,
        PresentConjugationDataContainer.getInstance().emphasizedConnectedPronounList
    )

    companion object {
        val instance = AugmentedPassivePresentConjugator()
    }
}