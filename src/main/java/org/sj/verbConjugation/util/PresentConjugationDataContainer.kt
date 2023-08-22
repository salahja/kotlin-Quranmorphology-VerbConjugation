package org.sj.verbConjugation.util;

import static org.sj.conjugator.utilities.ArabicLiterals.Damma;
import static org.sj.conjugator.utilities.ArabicLiterals.Fatha;
import static org.sj.conjugator.utilities.ArabicLiterals.Kasra;
import static org.sj.conjugator.utilities.ArabicLiterals.Sukun;

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * يحتوي على  المعلومات  الصرفية المطلوبة لتصريف الأفعال  في المضارع
 * <p>Title: Sarf</p>
 * <p>Description: برنامج التصريف</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class PresentConjugationDataContainer {
    private static final PresentConjugationDataContainer instance = new PresentConjugationDataContainer();
    //أحرف المضارعة حسب الضمير
    private final List<String> cpList = new ArrayList<>(13);
    //قائمة حركات عين الفعل حسب باب التصريف
    private final List<String> dpr2List = new ArrayList<String>(6);
    //قائمة  حركات لام الفعل حسب ضمير الرفع
    //مرفوع
    private final List<String> nominativeLastDprList = new ArrayList<>(13);
    //منصوب
    private final List<String> accusativeLastDprList = new ArrayList<>(13);
    //مجزوم
    private final List<String> jussiveLastDprList = new ArrayList<String>(13);
    //مؤكد
    private final List<String> emphasizedLastDprList = new ArrayList<String>(13);
    //قائمة ضمائر الرفع المتصلة
    //مرفوع
    private final List<String> nominativeConnectedPronounList = new ArrayList<String>(13);
    //منصوب
    private final List<String> accusativeConnectedPronounList = new ArrayList<String>(13);
    //مجزوم
    private final List<String> jussiveConnectedPronounList = new ArrayList<String>(13);
    //مؤكد
    private final List<String> emphasizedConnectedPronounList = new ArrayList<>(13);

    private PresentConjugationDataContainer() {
        //تهيئة القيم
    /*
    dpr2List.add(ArabCharUtil.DAMMA);
    dpr2List.add(ArabCharUtil.KASRA);
    dpr2List.add(ArabCharUtil.FATHA);
    dpr2List.add(ArabCharUtil.FATHA);
    dpr2List.add(ArabCharUtil.DAMMA);
    dpr2List.add(ArabCharUtil.KASRA);

     */
        //تهيئة القيم
        //   dpr2List.add(ArabCharUtil.DAMMA);
        //  dpr2List.add(ArabCharUtil.KASRA);
        // dpr2List.add(ArabCharUtil.FATHA);
        // dpr2List.add(ArabCharUtil.FATHA);
        // dpr2List.add(ArabCharUtil.DAMMA);
        // dpr2List.add(ArabCharUtil.KASRA);
        dpr2List.add(Damma.trim());//NASARA
        dpr2List.add(Kasra.trim());//ZARABA
        dpr2List.add(Fatha.trim());//FATHA
        dpr2List.add(Fatha.trim());//SAMIA
        dpr2List.add(Damma.trim());//KARUMA
        dpr2List.add(Kasra.trim());//HASIBA
        cpList.add("ي");
        cpList.add("ي");
        cpList.add("ي");
        cpList.add("ت");
        cpList.add("ت");
        cpList.add("ي");
        cpList.add("ت");
        cpList.add("ت");
        cpList.add("ت");
        cpList.add("ت");
        cpList.add("ت");
        cpList.add("أ");
        cpList.add("ن");
        nominativeLastDprList.add(Damma.trim());
        nominativeLastDprList.add(Fatha.trim());
        nominativeLastDprList.add(Damma.trim());
        nominativeLastDprList.add(Damma.trim());
        nominativeLastDprList.add(Fatha.trim());
        nominativeLastDprList.add(Sukun.trim());
        nominativeLastDprList.add(Damma.trim());
        nominativeLastDprList.add(Fatha.trim());
        nominativeLastDprList.add(Damma.trim());
        nominativeLastDprList.add(Kasra.trim());
        nominativeLastDprList.add(Sukun.trim());
        nominativeLastDprList.add(Damma.trim());
        nominativeLastDprList.add(Damma.trim());
        nominativeConnectedPronounList.add("");
        nominativeConnectedPronounList.add("انِ");
        nominativeConnectedPronounList.add("ونَ");
        nominativeConnectedPronounList.add("");
        nominativeConnectedPronounList.add("انِ");
        nominativeConnectedPronounList.add("نَ");
        nominativeConnectedPronounList.add("");
        nominativeConnectedPronounList.add("انِ");
        nominativeConnectedPronounList.add("ونَ");
        nominativeConnectedPronounList.add("ينَ");
        nominativeConnectedPronounList.add("نَ");
        nominativeConnectedPronounList.add("");
        nominativeConnectedPronounList.add("");
        accusativeLastDprList.add(Fatha.trim());//hua
        accusativeLastDprList.add(Fatha.trim());//huma-male
        accusativeLastDprList.add(Damma.trim());//hum
        accusativeLastDprList.add(Fatha.trim());//hiya
        accusativeLastDprList.add(Fatha.trim());//humafemale
        accusativeLastDprList.add(Sukun.trim());//.hunna
        accusativeLastDprList.add(Fatha.trim());//anta
        accusativeLastDprList.add(Fatha.trim());//;antuma
        accusativeLastDprList.add(Damma.trim());//antum
        accusativeLastDprList.add(Kasra.trim());//anti
        accusativeLastDprList.add(Sukun.trim());//antuna
        accusativeLastDprList.add(Fatha.trim());//ana
        accusativeLastDprList.add(Fatha.trim());//nahnu
        accusativeConnectedPronounList.add("");//huva
        accusativeConnectedPronounList.add("ا");//huma-ma
        accusativeConnectedPronounList.add("وا");//hum/
        accusativeConnectedPronounList.add("");//hiya
        accusativeConnectedPronounList.add("ا");//huma-female
        accusativeConnectedPronounList.add("نَ");//hunna
        accusativeConnectedPronounList.add("");//anta
        accusativeConnectedPronounList.add("ا");//antuma
        accusativeConnectedPronounList.add("وا");//antum
        accusativeConnectedPronounList.add("ي");//anti
        accusativeConnectedPronounList.add("نَ");//antunna
        accusativeConnectedPronounList.add("");//ana
        accusativeConnectedPronounList.add("");//nanhu
        jussiveLastDprList.add(ArabCharUtil.SKOON);
        jussiveLastDprList.add(ArabCharUtil.FATHA);
        jussiveLastDprList.add(ArabCharUtil.DAMMA);
        jussiveLastDprList.add(ArabCharUtil.SKOON);
        jussiveLastDprList.add(ArabCharUtil.FATHA);
        jussiveLastDprList.add(ArabCharUtil.SKOON);
        jussiveLastDprList.add(ArabCharUtil.SKOON);
        jussiveLastDprList.add(ArabCharUtil.FATHA);
        jussiveLastDprList.add(ArabCharUtil.DAMMA);
        jussiveLastDprList.add(ArabCharUtil.KASRA);
        jussiveLastDprList.add(ArabCharUtil.SKOON);
        jussiveLastDprList.add(ArabCharUtil.SKOON);
        jussiveLastDprList.add(ArabCharUtil.SKOON);
        jussiveConnectedPronounList.add("");
        jussiveConnectedPronounList.add("ا");
        jussiveConnectedPronounList.add("وا");
        jussiveConnectedPronounList.add("");
        jussiveConnectedPronounList.add("ا");
        jussiveConnectedPronounList.add("نَ");
        jussiveConnectedPronounList.add("");
        jussiveConnectedPronounList.add("ا");
        jussiveConnectedPronounList.add("وا");
        jussiveConnectedPronounList.add("ي");
        jussiveConnectedPronounList.add("نَ");
        jussiveConnectedPronounList.add("");
        jussiveConnectedPronounList.add("");
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Kasra.trim());
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Damma.trim());
        emphasizedLastDprList.add(Sukun.trim());
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Fatha.trim());
        emphasizedLastDprList.add(Damma.trim());
        emphasizedLastDprList.add(Sukun.trim());
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("انِّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَانِّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("انِّ");
        emphasizedConnectedPronounList.add("انِّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَانِّ");
    }

    public static PresentConjugationDataContainer getInstance() {
        return instance;
    }

    public List<String> getNominativeLastDprList() {
        return nominativeLastDprList;
    }

    public List<String> getNominativeConnectedPronounList() {
        return nominativeConnectedPronounList;
    }

    public List<String> getEmphasizedLastDprList() {
        return emphasizedLastDprList;
    }

    public List<String> getEmphasizedConnectedPronounList() {
        return emphasizedConnectedPronounList;
    }

    public List<String> getJussiveLastDprList() {
        return jussiveLastDprList;
    }

    public List<String> getJussiveConnectedPronounList() {
        return jussiveConnectedPronounList;
    }

    public List<String> getAccusativeLastDprList() {
        return accusativeLastDprList;
    }

    public List<String> getAccusativeConnectedPronounList() {
        return accusativeConnectedPronounList;
    }

    /**
     * الحصول  على حركة عين الفعل حسب باب تصريف الفعل
     *
     * @param root TripleVerb
     * @return String
     */
    public String getDpr2(UnaugmentedTrilateralRoot root) {
        //بسبب أن ترقيم الباب التصريفي يبدأ من الواحد على حين أن القائمة تبدأ من الصفر جرى طرح العدد واحد
        return dpr2List.get((Integer.parseInt(root.getConjugation()) - 1));
    }

    /**
     * الحصول  على حرف المضارع حسب الضمير
     *
     * @param pronounIndex int
     * @return String
     */
    public String getCp(int pronounIndex) {
        return cpList.get(pronounIndex);
    }
}
