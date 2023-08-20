package org.sj.verbConjugation.trilateral.augmented.imperative

import org.sj.verbConjugation.util.ImperativeConjugationDataContainer

class AugmentedImperativeConjugator private constructor() {
    val notEmphsizedConjugator = AbstractAugmentedImperativeConjugator(
        ImperativeConjugationDataContainer.getInstance().lastDimList,
        ImperativeConjugationDataContainer.getInstance().connectedPronounList
    )
    val emphsizedConjugator = AbstractAugmentedImperativeConjugator(
        ImperativeConjugationDataContainer.getInstance().emphasizedLastDimList,
        ImperativeConjugationDataContainer.getInstance().emphasizedConnectedPronounList
    )

    companion object {
        val instance = AugmentedImperativeConjugator()
    }
}