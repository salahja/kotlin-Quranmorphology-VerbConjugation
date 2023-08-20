package org.sj.verbConjugation.util;

import java.util.ArrayList;
import java.util.List;

public class TrilateralKovRuleList {
    private final List<TrilateralKovRule> rules = new ArrayList<TrilateralKovRule>(33);

    public TrilateralKovRuleList() {
    }

    public void addRule(TrilateralKovRule rule) {
        rules.add(rule);
    }

    public List<TrilateralKovRule> getRules() {
        return rules;
    }
}