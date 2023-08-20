package org.sj.verbConjugation.util;

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution;
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;

import java.util.LinkedList;
import java.util.List;

public class VerbLamAlefModifier extends SubstitutionsApplier {
    private static final VerbLamAlefModifier instance = new VerbLamAlefModifier();
    List<InfixSubstitution> substitutions = new LinkedList<>();

    private VerbLamAlefModifier() {
        substitutions.add(new InfixSubstitution("لَا", "لا"));// EX: (قالا)
        substitutions.add(new InfixSubstitution("لَّا", "لاَّ"));// EX: (انْشَلاَّ)
        substitutions.add(new InfixSubstitution("لَأ", "لأ"));// EX: (مَلأَ، مَلأْتُ)
        substitutions.add(new InfixSubstitution("لًا", "لاً"));// EX: (حملاً)

    }

    public static VerbLamAlefModifier getInstance() {
        return instance;
    }

    public void apply(org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult conjResult) {
        apply(conjResult.getFinalResult(), null);
    }

    public void apply(MazeedConjugationResult conjResult) {
        apply(conjResult.getFinalResult(), null);
    }
    // public void apply(org.sj.verb.quadriliteral.ConjugationResult conjResult) {
    // apply(conjResult.getFinalResult(), null);
    //}

    public List getSubstitutions() {
        return substitutions;
    }

}
