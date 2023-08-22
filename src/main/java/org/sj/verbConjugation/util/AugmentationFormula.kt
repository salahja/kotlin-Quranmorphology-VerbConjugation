package org.sj.verbConjugation.util;

public class AugmentationFormula {
    private int formulaNo;
    private char verbtype;

    public AugmentationFormula() {
    }

    public char getVerbtype() {
        return verbtype;
    }

    public void setVerbtype(char verbtype) {
        this.verbtype = verbtype;
    }

    public int getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(int formulaNo) {
        this.formulaNo = formulaNo;
    }
}