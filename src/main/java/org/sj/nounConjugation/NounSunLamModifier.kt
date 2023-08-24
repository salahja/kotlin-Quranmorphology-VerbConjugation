package org.sj.nounConjugation;

import org.sj.verbConjugation.trilateral.Substitution.Substitution;
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier;
import org.sj.verbConjugation.trilateral.TrilateralRoot;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult;

import java.util.ArrayList;
import java.util.Iterator;
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
public class NounSunLamModifier extends SubstitutionsApplier {
    private static final NounSunLamModifier instance = new NounSunLamModifier();
    protected static List appliedProunounsIndecies = new ArrayList(13);

    static {
        for (int i = 0; i < 18; i++) {
            appliedProunounsIndecies.add(i + 1 + "");
        }
    }

    List substitutions = new LinkedList();

    private NounSunLamModifier() {
        List sunLetters = new LinkedList();
        sunLetters.add("ت");
        sunLetters.add("ث");
        sunLetters.add("د");
        sunLetters.add("ذ");
        sunLetters.add("ر");
        sunLetters.add("ز");
        sunLetters.add("س");
        sunLetters.add("ش");
        sunLetters.add("ص");
        sunLetters.add("ض");
        sunLetters.add("ط");
        sunLetters.add("ظ");
        sunLetters.add("ل");
        sunLetters.add("ن");
        substitutions.add(new ListedInfixSubstitution(sunLetters, "الSLَ", "الSLَّ"));
        substitutions.add(new ListedInfixSubstitution(sunLetters, "الSLُ", "الSLُّ"));
        substitutions.add(new ListedInfixSubstitution(sunLetters, "الSLِ", "الSLِّ"));
    }

    public static NounSunLamModifier getInstance() {
        return instance;
    }

    public void apply(List<Object> finalResult, ConjugationResult conjResult) {
        apply(conjResult.getFinalResult(), (ConjugationResult) null);
    }

    public void apply(MazeedConjugationResult conjResult) {
        apply(conjResult.getFinalResult(), (ConjugationResult) null);
    }

    //todo
/*

  public void apply(org.sj.verb.quadriliteral.ConjugationResult conjResult) {
    apply(conjResult.getFinalResult(), null);
  }

 */
    public List getSubstitutions() {
        return substitutions;
    }

    protected List getAppliedPronounsIndecies() {
        return appliedProunounsIndecies;
    }

    class ListedInfixSubstitution extends Substitution {
        private final List probableChars;

        public ListedInfixSubstitution(List probableChars, String segment, String result) {
            super(segment, result);
            this.probableChars = probableChars;
        }

        /**
         * @param word String
         * @return String
         */
        public String apply(String word, TrilateralRoot root) {
            Iterator iter = probableChars.iterator();
            while (iter.hasNext()) {
                String sl = (String) iter.next();
                String appliedResut = apply(word, sl);
                if (appliedResut != null) {
                    return appliedResut;
                }
            }
            return null;
        }

        public String apply(String word, String sl) {
            String wordSegment =    getSegment().replaceAll("SL",sl);
            if (word.indexOf(wordSegment) == -1) {
                return null;
            }
            String replacedResult =    getResult().replaceAll("SL",sl);

            return word.replaceAll(wordSegment, replacedResult);
        }

    }

}
