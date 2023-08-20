package com.example.mushafconsolidatedimport

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log















 





class Config constructor() {
    // public String lang;
    var rtl: Boolean = false
    var showTranslation: Boolean = false
    var wordByWord: Boolean = false
    var fullWidth: Boolean = false
    var keepScreenOn: Boolean = false
    var enableAnalytics: Boolean = false
    var fontArabic: String? = null
    var fontSizeArabic: String? = null
    var fontSizeTranslation: Int = 0
    var showErab: Boolean = false
    fun load(context: Context?) {
        Log.d("Config", "Load")
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        try {
            loadDefault()
            fontArabic =
                sp.getString(Config.Companion.ARABIC_FONT, Config.Companion.defaultArabicFont)
            fontSizeArabic = sp.getString(
                Config.Companion.FONT_SIZE_ARABIC,
                Config.Companion.defaultFontSizeArabic
            )
            Log.d("Config", "Loading Custom")
        } catch (e: Exception) {
            loadDefault()
            Log.d("Config", "Exception Loading Defaults")
        }
    }

    fun loadDefault() {
        fontArabic = Config.Companion.defaultArabicFont
        fontSizeArabic = Config.Companion.defaultFontSizeArabic
    }

    /*public void save(Context context) {
        Log.d("Config","Save");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.putString(LANG, lang);
        ed.putBoolean(SHOW_TRANSLATION, showTranslation);
        ed.putBoolean(WORD_BY_WORD, wordByWord);
        ed.putBoolean(KEEP_SCREEN_ON, keepScreenOn);
        ed.putString(FONT_SIZE_ARABIC, "" + fontSizeArabic);
        ed.putString(FONT_SIZE_TRANSLATION, "" + fontSizeTranslation);
        ed.commit();
    }*/
    private fun getStringInt(sp: SharedPreferences, key: String, defValue: Int): Int {
        return sp.getString(key, Integer.toString(defValue))!!.toInt()
    } /*  public boolean loadFont() {
      if (loadedFont != Config.fontArabic) {
          String name;
          switch (config.fontArabic) {
              case Config.FONT_NASKH:
                  name = "naskh.otf";
                  break;
              case Config.FONT_NOOREHUDA:
                  name = "noorehuda.ttf";
                  break;
              case Config.FONT_ME_QURAN:
                  name = "me_quran.ttf";
                  break;
              default:
                  name = "qalam.ttf";
          }
          try {
              NativeRenderer.loadFont(getAssets().open(name));
              loadedFont = config.fontArabic;
          } catch (IOException e) {
              e.printStackTrace();
              loadedFont = -1;
              return false;
          }
      }
      return true;
  }*/

    companion object {
        val FONT_QALAM_MAJEED: Int = 0
        val FONT_HAFS: Int = 1
        val FONT_NOOREHUDA: Int = 2
        val FONT_ME_QURAN: Int = 3
        val FONT_MAX: Int = 3
        val LANG: String = "lang"
        val LANG_BN: String = "bn"
        val LANG_EN: String = "en"
        val LANG_INDO: String = "indo"
        val SHOW_TRANSLATION: String = "showTranslation"
        val SHOW_Erab: String = "showErab"
        val WORD_BY_WORD: String = "wordByWord"
        val KEEP_SCREEN_ON: String = "keepScreenOn"
        val ARABIC_FONT: String = "arabicFont"
        val FONT_SIZE_ARABIC: String = "fontSizeArabic"
        val FONT_SIZE_TRANSLATION: String = "fontSizeTranslation"
        val FONT_SIZE_ERAB: String = "fontSizeErab"
        val FIRST_RUN: String = "firstRun"
        val DATABASE_VERSION: String = "dbVersion"
        val defaultLang: String = "en"
        val defaultShowTranslation: Boolean = true
        val defaultShowErab: Boolean = true
        val defaultWordByWord: Boolean = true
        val defaultKeepScreenOn: Boolean = true
        val defaultArabicFont: String = "Uthmani.oft"
        val defaultFontSizeArabic: String = "20"
        val defaultFontSizeTranslation: String = "14"
        val defaultFontSizeErab: String = "14"
    }
}