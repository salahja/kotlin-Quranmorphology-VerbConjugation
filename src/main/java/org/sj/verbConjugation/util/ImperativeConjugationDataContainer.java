package org.sj.verbConjugation.util;

import java.util.ArrayList;
import java.util.List;

/**
 * يحتوي على  المعلومات  الصرفية المطلوبة لتصريف الأفعال  في الأمر
 * <p>Title: Sarf</p>
 * <p>Description: برنامج التصريف</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class ImperativeConjugationDataContainer {
    private static final ImperativeConjugationDataContainer instance = new ImperativeConjugationDataContainer();
    //قائمة حركات عين الفعل حسب باب التصريف
    //الأمر غير المؤكد
    private final List<String> lastDimList = new ArrayList<>(13);
    //الأمر المؤكد
    private final List<String> emphasizedLastDimList = new ArrayList<>(13);
    //قائمة ضمائر الرفع المتصلة
    //الأمر غير المؤكد
    private final List<String> connectedPronounList = new ArrayList<String>(13);
    //الأمر المؤكد
    private final List<String> emphasizedConnectedPronounList = new ArrayList<String>(13);

    private ImperativeConjugationDataContainer() {
        lastDimList.add("");
        lastDimList.add("");
        lastDimList.add(ArabCharUtil.SKOON);
        lastDimList.add(ArabCharUtil.KASRA);
        lastDimList.add(ArabCharUtil.FATHA);
        lastDimList.add(ArabCharUtil.DAMMA);
        lastDimList.add(ArabCharUtil.SKOON);
        lastDimList.add("");
        lastDimList.add("");
        lastDimList.add("");
        lastDimList.add("");
        lastDimList.add("");
        lastDimList.add("");
        connectedPronounList.add("");
        connectedPronounList.add("");
        connectedPronounList.add("");
        connectedPronounList.add("ي");
        connectedPronounList.add("ا");
        connectedPronounList.add("وا");
        connectedPronounList.add("نَ");
        connectedPronounList.add("");
        connectedPronounList.add("");
        connectedPronounList.add("");
        connectedPronounList.add("");
        connectedPronounList.add("");
        connectedPronounList.add("");
        emphasizedLastDimList.add("");
        emphasizedLastDimList.add("");
        emphasizedLastDimList.add(ArabCharUtil.FATHA);
        emphasizedLastDimList.add(ArabCharUtil.KASRA);
        emphasizedLastDimList.add(ArabCharUtil.FATHA);
        emphasizedLastDimList.add(ArabCharUtil.DAMMA);
        emphasizedLastDimList.add(ArabCharUtil.SKOON);
        emphasizedLastDimList.add("");
        emphasizedLastDimList.add("");
        emphasizedLastDimList.add("");
        emphasizedLastDimList.add("");
        emphasizedLastDimList.add("");
        emphasizedLastDimList.add("");
        emphasizedConnectedPronounList.add("");
        emphasizedConnectedPronounList.add("");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("انِّ");
        emphasizedConnectedPronounList.add("نَّ");
        emphasizedConnectedPronounList.add("نَانِّ");
        emphasizedConnectedPronounList.add("");
        emphasizedConnectedPronounList.add("");
        emphasizedConnectedPronounList.add("");
        emphasizedConnectedPronounList.add("");
        emphasizedConnectedPronounList.add("");
        emphasizedConnectedPronounList.add("");
    }

    public static ImperativeConjugationDataContainer getInstance() {
        return instance;
    }

    public List<String> getEmphasizedConnectedPronounList() {
        return emphasizedConnectedPronounList;
    }

    public List<String> getEmphasizedLastDimList() {
        return emphasizedLastDimList;
    }

    public List<String> getConnectedPronounList() {
        return connectedPronounList;
    }

    public List<String> getLastDimList() {
        return lastDimList;
    }

    /**
     * الحصول  على  حركة لام الفعل حسب الضمير
     * الأمر غير المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    public String getLastDim(int pronounIndex) {
        return lastDimList.get(pronounIndex);
    }

    /**
     * الحصول  على  حركة لام الفعل حسب الضمير
     * الأمر  المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    public String getEmphasizedLastDim(int pronounIndex) {
        return emphasizedLastDimList.get(pronounIndex);
    }

    /**
     * الحصول على ضمائر الرفع المتصلة
     * الأمر غير المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    public String getConnectedPronoun(int pronounIndex) {
        return connectedPronounList.get(pronounIndex);
    }

    /**
     * الحصول على ضمائر الرفع المتصلة
     * الأمر  المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    public String getEmphasizedConnectedPronoun(int pronounIndex) {
        return emphasizedConnectedPronounList.get(pronounIndex);
    }
}