package com.example.mushafconsolidated.settingsimport

import android.os.Environment
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import java.io.File


class Constants constructor() {
    var GREEN: Int = 2
    var YELLOW: Int = 1
    var WHITE: Int = 0
    var NA: Int = -1

    companion object {
        //  String DownloadLink = "http://192.168.29.28.xip.io/drupal/sites/default/files/2021-04/QuranImages.zip";
        // data domain
        val HOST: String = "http://192.168.29.28.sslip.io/drupal/sites/default/files/2021-05/"

        //     public static final String HOSTDATABASEURL ="http://192.168.29.28.xip.io/drupal/sites/default/files/2021-04/QuranDatabase.db_3.zip";
        //   public static final String HOSTDATABASEURL ="http://localhost/drupal/sites/default/files/2021-04/QuranDatabase.db_3.zip";
        val HOSTQURNPAGESURL: String =
            "http://192.168.29.28.xip.io/drupal/sites/default/files/2021-04/QuranImages.zip"

        //public static final String HOSTDATABASEURL=  "http://192.168.29.28.sslip.io/drupal/sites/default/files/2021-05/QuranDatabase_0.zip";
        val HOSTDATABASEURL: String =
            "http://192.168.29.28.sslip.io/drupal/sites/default/files/2021-05/QuranDatabase.zip"
        val HOSTQURANTRANSLATION: String =
            "http://192.168.29.28.sslip.io/drupal/sites/default/files/2021-05/"
        val DATABASEZIP: String = "qurangrammar.db.zip"

        // public static final String DATABASENAME="QuranDatabase.db";
        val DATABASENAME: String = "qurangrammar.db"
        val QURANZIP: String = "QuranImages.zip"
        val VERBDATABASE: String = "conjugator.db"
        val SQLDATABASENAME: String = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Mushafapplication" + File.separator + Constants.Companion.DATABASENAME

        // Numerics
        val DEFAULT_NIGHT_MODE_TEXT_BRIGHTNESS: Int = 255
        val DEFAULT_TEXT_SIZE: Int = 15

        // 10 days in ms
        val TRANSLATION_REFRESH_TIME: Int = 60 * 60 * 24 * 10 * 1000

        // 1 hour in ms
        val MIN_TRANSLATION_REFRESH_TIME: Int = 60 * 60 * 1000

        // quranapp
        val QURAN_APP_BASE: String = "http://quranapp.com/"
        val QURAN_APP_ENDPOINT: String = "http://quranapp.com/note"

        // Notification Ids
        val NOTIFICATION_ID_DOWNLOADING: Int = 1
        val NOTIFICATION_ID_DOWNLOADING_COMPLETE: Int = 2
        val NOTIFICATION_ID_DOWNLOADING_ERROR: Int = 3
        val NOTIFICATION_ID_AUDIO_PLAYBACK: Int = 4
        val NOTIFICATION_ID_AUDIO_UPDATE: Int = 5

        // Notification channels
        val AUDIO_CHANNEL: String = "quran_audio_playback"
        val DOWNLOAD_CHANNEL: String = "quran_download_progress"

        // Unique work names
        val AUDIO_UPDATE_UNIQUE_WORK: String = "audio_update_unique_work"

        // Settings Key (some of these have corresponding values in preference_keys.xml)
        val PREF_SHOW_ERAB: String = "showErabKey"
        val PREF_SHOW_TRANSLATION: String = "showTranslationKey"
        val PREF_APP_LOCATION: String = "appLocation"
        val PREF_USE_ARABIC_NAMES: String = "useArabicNames"
        val PREF_LAST_PAGE: String = "lastPage"
        val PREF_LOCK_ORIENTATION: String = "lockOrientation"
        val PREF_LANDSCAPE_ORIENTATION: String = "landscapeOrientation"
        val PREF_TRANSLATION_TEXT_SIZE: String = "translationTextSize"
        val PREF_ACTIVE_TRANSLATION: String = "activeTranslation"
        val PREF_ACTIVE_TRANSLATIONS: String = "activeTranslations"
        val PREF_NIGHT_MODE: String = "nightMode"
        val PREF_NIGHT_MODE_TEXT_BRIGHTNESS: String = "nightModeTextBrightness"
        val PREF_DEFAULT_QARI: String = "defaultQari"
        val PREF_SHOULD_FETCH_PAGES: String = "shouldFetchPages"
        val PREF_OVERLAY_PAGE_INFO: String = "overlayPageInfo"
        val PREF_DISPLAY_MARKER_POPUP: String = "displayMarkerPopup"
        val PREF_IMMERSIVE_IN_PORTRAIT: String = "immersiveInPortrait"
        val PREF_HIGHLIGHT_BOOKMARKS: String = "highlightBookmarks"
        val PREF_AYAH_BEFORE_TRANSLATION: String = "ayahBeforeTranslation"
        val PREF_PREFER_STREAMING: String = "preferStreaming"
        val PREF_DOWNLOAD_AMOUNT: String = "preferredDownloadAmount"
        val PREF_LAST_UPDATED_TRANSLATIONS: String = "lastTranslationsUpdate"
        val PREF_HAVE_UPDATED_TRANSLATIONS: String = "haveUpdatedTranslations"
        val PREF_USE_NEW_BACKGROUND: String = "useNewBackground"
        val PREF_USE_VOLUME_KEY_NAV: String = "volumeKeyNavigation"
        val PREF_SORT_BOOKMARKS: String = "sortBookmarks"
        val PREF_GROUP_BOOKMARKS_BY_TAG: String = "groupBookmarksByTag"
        val PREF_SHOW_RECENTS: String = "showRecents"
        val PREF_SHOW_DATE: String = "showDate"
        val PREF_VERSION: String = "version"
        val PREF_DEFAULT_IMAGES_DIR: String = "defaultImagesDir"
        val PREF_TRANSLATION_MANAGER: String = "translationManagerKey"
        val PREF_AUDIO_MANAGER: String = "audioManagerKey"
        val PREF_PAGE_TYPE: String = "pageTypeKey"
        val PREF_DID_PRESENT_PERMISSIONS_DIALOG: String = "didPresentStoragePermissionDialog"
        val PREF_WAS_SHOWING_TRANSLATION: String = "wasShowingTranslation"
        val DEBUG_DID_DOWNLOAD_PAGES: String = "debugDidDownloadPages"
        val DEBUG_PAGE_DOWNLOADED_PATH: String = "debugPageDownloadedPath"
        val DEBUG_PAGES_DOWNLOADED_TIME: String = "debugPagesDownloadedTime"
        val DEBUG_PAGES_DOWNLOADED: String = "debugPagesDownloaded"
        val PREF_SURA_TRANSLATED_NAME: String = "suraTranslatedName"

        var FILEPATH = QuranGrammarApplication.context!!.getExternalFilesDir(null)!!
            .absolutePath + "/" + QuranGrammarApplication.context!!.resources.getString(R.string.app_folder_path)

        var SURAH_INDEX: String = "index"
        var MUSHAFDISPLAY: String = "display"
        var LAST_INDEX: String = "index_sura"
        var LAST_INDEX_Scroll: String = "scroll"
        var PERMISSION_STATE: String = "state"
        var NightMode_STATE: String = "night mode "
        var BackColor_STATE: String = "bg_color"
        var SCORE: String = "score"
        var USERS_KEY: String = "users"
        var REVIWES_KEY: String = "reviews"
        var USER_NAME: String = "user_name"
        var USER_UUID: String = "uuid"
        var PAGE_INDEX: String = "pageIndex"
        var JUZ_INDEX: String = "Juz"
        var SURAH_GO_INDEX: String = "goSuraIndex"
        var AYAH_GO_INDEX: String = "ayahGo"
        var RECORD_ITEM: String = "record"
        var AUDIO_ITEMS: String = "ayahitems"
    }
}