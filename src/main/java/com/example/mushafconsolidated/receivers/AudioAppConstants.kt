package com.example.mushafconsolidated.receiversimport

 

 

 

 
 
 
 
  
 
 
 
 
 
 

open interface AudioAppConstants {
    open interface General {
        companion object {
            val SEARCH_TEXT: String = "SearchText"
            val PAGE_NUMBER: String = "PageNumber"
            val PAGE: String = "Page"
            val BOOK_MARK: String = "bookmark"
            val BOOK_MARK_TAFSEER: String = "bookmark_tafseer"
            val AYA_ID: String = "aya_id"
            val SURA_ID: String = "sura_id"
        }
    }

    /**
     * File and folder paths constants
     */
    open interface Paths {
        companion object {
            val MAIN_DATABASE_PATH: String = "/quran_fekra_computers/quran.sqlite"
            val TAFSEER_DATABASE_PATH: String = "/quran_fekra_computers/tafaseer"
            val TAFSEER_LINK: String = "http://quran.islam-db.com/data/tafaseer/tafseer"
        }
    }

    /**
     * File extensions constants
     */
    open interface Extensions {
        companion object {
            val MP3: String = ".mp3"
            val ZIP: String = ".zip"
            val SQLITE: String = ".sqlite"
        }
    }

    /**
     * Media player constants
     */
    open interface MediaPlayer {
        companion object {
            val INTENT: String = "NewShowMushafActivity"
            val PLAY: String = "play"
            val PAUSE: String = "pause"
            val STOP: String = "stop"
            val RESUME: String = "resume"
            val FORWARD: String = "forward"
            val BACK: String = "back"
            val REPEAT_ON: String = "repeatOn"
            val REPEAT_OFF: String = "repeatOff"
            val STREAM_LINK: String = "streamLink"
            val AYAT: String = "ayat"
            val LOCATIONS_LIST: String = "aya_list_locations"
            val VERSE: String = "aya"
            val PLAYING: String = "playing"
            val OTHER_PAGE: String = "other_page"
            val PAGE: String = "page"
            val SURAH: String = "surah"
            val READER: String = "reader"
            val EVRYAYA: String = "evryayah"
            val ONE_VERSE: String = "one_verse"
            val FULLSURAPATH: String = "fullsurah"
            val SURA: String = "sura"
        }
    }

    /**
     * Download constants
     */
    open interface Download {
        companion object {
            val INTENT: String = "DownloadStatusReciver"
            val DOWNLOAD_URL: String = "download_url"
            val DOWNLOAD_LOCATION: String = "download_location"
            val DOWNLOAD: String = "download"
            val SUCCESS: String = "success"
            val FAILED: String = "failed"
            val NUMBER: String = "Number"
            val MAX: String = "max"
            val TYPE: String = "download_type"
            val IN_DOWNLOAD: String = "in download"
            val IN_EXTRACT: String = "in extract"
            val FILES: String = "Files"
            val UNZIP: String = "unzipped"
            val DOWNLOAD_LINKS: String = "download_links"
        }
    }

    /**
     * Image highlight constants
     */
    open interface Highlight {
        companion object {
            val INTENT: String = "HighlightAya"
            val VERSE_NUMBER: String = "ayaNumber"
            val SORA_NUMBER: String = "soraNumber"
            val PAGE_NUMBER: String = "pageNumber"
            val ARG_SECTION_NUMBER: String = "section_number"
            val RESET_IMAGE: String = "RESETIMAGE"
            val RESET: String = "reset"
            val INTENT_FILTER: String = "Quran.mindtrack.image"
        }
    }

    /**
     * applications preferences constants
     */
    open interface Preferences {
        companion object {
            //download
            val DOWNLOAD_FAILED: Int = 400
            val DOWNLOAD_SUCCESS: Int = 200

            //download types
            val TAFSEER: Int = 1
            val IMAGES: Int = 2

            //shared preference keys
            val CONFIG: String = "configurations"
            val DOWNLOAD_STATUS: String = "download_status"
            val DOWNLOAD_STATUS_TEXT: String = "download_status_text"
            val DOWNLOAD_TYPE: String = "download_type"
            val DOWNLOAD_ID: String = "download_id"
            val LAST_PAGE_NUMBER: String = "last_page_number"
            val SCREEN_RESOLUTION: String = "screen_resolution"
            val VOLUME_NAVIGATION: String = "volume"
            val LANGUAGE: String = "app_language"
            val DEFAULT_EXPLANATION: String = "default_tafseer"
            val ORIENTATION: String = "orientation"
            val ARABIC_MOOD: String = "language"
            val NIGHT_MOOD: String = "night"
            val TRANSLATIONS: String = "translations"
            val AYA_APPEAR: String = "aya"
            val TRANSLATION_SIZE: String = "size"
            val SELECT_VERSE: String = "select"
            val STREAM: String = "stream"
        }
    }

    /**
     * Tafseer constants
     */
    open interface Tafseer {
        companion object {
            val INTENT: String = "tafseerMood"
            val MOOD: String = "tafseer_mode"
            val AYA: String = "aya"
            val SORA: String = "sora"
        }
    }
}