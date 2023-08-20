package org.sj.verbConjugation.util;

import com.example.utility.QuranGrammarApplication;

import java.util.ArrayList;

import database.VerbDatabaseUtils;
import database.entity.kov;

public class KovRulesManager {
    private static final KovRulesManager instance = new KovRulesManager();
    private String c1;
    private String c2;
    private String c3;
    private int kov;
    private String desc;
    private String example;
    private TrilateralKovRuleList trilateralRulesLists;
    private ArrayList<database.entity.kov> trilateralRulesList = new ArrayList<>();

    private KovRulesManager() {
        String tri = "./src/main/resources/db/Trilateralkov.xml";
        String quad = "./src/main/resources/db/Quadrilateralkov.xml";
        try {
            //    trilateralRulesList = buildTrilateral(new File("/Trilateralkov.xml"));
            //   quadrilateralRulesList = buildQuadrilateral(new File("/Quadrilateralkov.xml"));
            trilateralRulesList = buildTrilateral();
            //   quadrilateralRulesList = buildQuadrilateral(new File(quad));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static KovRulesManager getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        char c1 = 'ح';
        char c2 = 'ي';
        char c3 = 'ح';
        char c4 = 'ي';
        //   ////System.out.println(""+ KovRulesManager.getInstance().getQuadrilateralKov(c1,c2,c3,c4));
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public int getKov() {
        return kov;
    }

    public void setKov(int kov) {
        this.kov = kov;
    }
    // private QuadrilateralKovRuleList quadrilateralRulesList;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    private ArrayList<database.entity.kov> buildTrilateral() throws Exception {
        // Context context = DarkThemeApplication.getContext();
        VerbDatabaseUtils utils = new VerbDatabaseUtils(QuranGrammarApplication.context);
        trilateralRulesList = (ArrayList<database.entity.kov>) utils.getKov();
        return trilateralRulesList;
    }

    /**
     * the rules is sorted according to its application, so the first applied rule
     * will be returned its kov
     *
     * @param c1 char
     * @param c2 char
     * @param c3 char
     * @return int
     */
    public int getTrilateralKov(char c1, char c2, char c3) {
        TrilateralKovRule rule = getTrilateralKovRule(c1, c2, c3);
        return rule != null ? rule.getKov() : -1;
    }

    public TrilateralKovRule getTrilateralKovRule(char c1, char c2, char c3) {
        for (kov iter : trilateralRulesList) {
            setC1(iter.getC1());
            setC2(iter.getC2());
            setC3(iter.getC3());
            setKov(Integer.parseInt(iter.getKov()));
            setExample(iter.getExample());
            setDesc(iter.getRulename());
            char cc1 = iter.getC1().charAt(0);
            char cc2 = iter.getC2().charAt(0);
            char cc3 = iter.getC3().charAt(0);
            boolean ifrule = check(c1, c2, c3);
            if (ifrule) {
                TrilateralKovRule rule = new TrilateralKovRule();
                rule.setC1(iter.getC1());
                rule.setC2(iter.getC2());
                rule.setC3(iter.getC3());
                rule.setKov(Integer.parseInt(iter.getKov()));
                rule.setExample(iter.getExample());
                rule.setDesc(iter.getRulename());
                return rule;
            }
        }
        return null;
    }


  /*
   public int getQuadrilateralKov(char c1, char c2, char c3, char c4) {
    QuadrilateralKovRule rule = getQuadrilateralKovRule(c1, c2, c3 ,c4);
    return rule!= null? rule.getKov(): -1;
  }

  public QuadrilateralKovRule getQuadrilateralKovRule(char c1, char c2, char c3, char c4) {
    Iterator iter = quadrilateralRulesList.getRules().iterator();
    while (iter.hasNext()) {
      QuadrilateralKovRule rule = (QuadrilateralKovRule) iter.next();
      if (rule.check(c1, c2, c3, c4))
        return rule;
    }
    return null;
  }


   */

    public boolean check(char verbC1, char verbC2, char verbC3) {
        boolean b1 = (c1.equals("?")) || c1.equals("null") || (c1.equals(verbC1 + ""));
        boolean b2 = false, b3 = false;
        if (c2.equalsIgnoreCase("c3") && c3.equalsIgnoreCase("c2")) {
            b2 = b3 = (verbC2 == verbC3);
        } else {
            b2 = (c2.equals("?")) || c2.equals("null") || (c2.equals(verbC2 + ""));
            b3 = (c3.equals("?")) || c3.equals("null") || (c3.equals(verbC3 + ""));
        }
        return b1 && b2 && b3;
    }

}
