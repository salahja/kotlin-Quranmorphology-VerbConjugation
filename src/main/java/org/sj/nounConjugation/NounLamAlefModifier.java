package org.sj.nounConjugation;

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution;
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class NounLamAlefModifier extends SubstitutionsApplier {
    private static final NounLamAlefModifier instance = new NounLamAlefModifier();
    protected static List appliedProunounsIndecies = new ArrayList(13);

    static {
        for (int i = 0; i < 18; i++) {
            appliedProunounsIndecies.add(i + 1 + "");
        }
    }

    List substitutions = new LinkedList();

    private NounLamAlefModifier() {
        substitutions.add(new InfixSubstitution("لَا", "لا"));// EX: (قالا)
        substitutions.add(new InfixSubstitution("لَّا", "لاَّ"));// EX: (انْشَلاَّ)
        substitutions.add(new InfixSubstitution("لَأ", "لأ"));// EX: (مَلأَ، مَلأْتُ)
        substitutions.add(new InfixSubstitution("لًا", "لاً"));// EX: (حملاً)

    }

    public static NounLamAlefModifier getInstance() {
        return instance;
    }

    public void apply(org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult conjResult) {
        apply(conjResult.getFinalResult(), null);
        //قد يوجد لام ألف أخرى تتطابق مع قانون أخر
        apply(conjResult.getFinalResult(), null);
    }

    public void apply(MazeedConjugationResult conjResult) {
        apply(conjResult.getFinalResult(), null);
        //قد يوجد لام ألف أخرى تتطابق مع قانون أخر
        apply(conjResult.getFinalResult(), null);
    }
//todo quadri
/*

  public void apply(org.sj.verb.quadriliteral.ConjugationResult conjResult) {
    apply(conjResult.getFinalResult(), null);
    //قد يوجد لام ألف أخرى تتطابق مع قانون أخر
    apply(conjResult.getFinalResult(), null);
  }
 */

    public List getSubstitutions() {
        return substitutions;
    }

    protected List getAppliedPronounsIndecies() {
        return appliedProunounsIndecies;
    }

}
