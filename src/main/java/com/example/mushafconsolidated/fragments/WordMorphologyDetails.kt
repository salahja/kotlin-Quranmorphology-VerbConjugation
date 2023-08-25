package com.example.mushafconsolidated.fragments


import android.text.SpannableString
import android.text.SpannableStringBuilder
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.model.CorpusWbwWord
import com.example.utility.CorpusConstants.verbfeaturesenglisharabic
import com.example.utility.CorpusUtilityorig


class WordMorphologyDetails : QuranMorphologyDetails {
    private var verbCorpusRootWord: ArrayList<VerbCorpus>? = null
    private var corpusNounWord: ArrayList<NounCorpus>? = null
    private var word: CorpusWbwWord

    constructor(word: CorpusWbwWord) {
        this.word = word
    }

    constructor(
        word: CorpusWbwWord,
        corpusNounWord: List<*>,
        verbCorpusRootWord: List<*>
    ) {
        this.word = word
        this.corpusNounWord = corpusNounWord as ArrayList<NounCorpus>?
        this.verbCorpusRootWord = verbCorpusRootWord as ArrayList<VerbCorpus>?
    }

    constructor(word: CorpusWbwWord, corpusNounWord: ArrayList<NounCorpus>?) {
        this.word = word
        this.corpusNounWord = corpusNounWord
    }//   tagspannable.setSpan(new ForegroundColorSpan(CYAN), 0, expandTagsone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//   SpannableString  tagspannable = null;

    // --Commented out by Inspection START (13/11/22, 3:02 PM):
    //    public WordMorphologyDetails() {
    //    }
    // --Commented out by Inspection STOP (13/11/22, 3:02 PM)
    val workBreakDown: SpannableString?
        get() {
            //   SpannableString  tagspannable = null;
            var tagspannable: SpannableString? = null
            val wordcount: Int = word.wordcount
            if (wordcount == 1) {
                val tagone: String = word.tagone!!
                var expandTagsone: String = QuranMorphologyDetails.Companion.expandTags(tagone)
                if ((tagone == "N")) {
                    expandTagsone = getNounDetails(word.detailsone)
                }
                if ((tagone == "V")) {
                    expandTagsone = getVerbDetails(expandTagsone, word.detailsone!!)
                }
                tagspannable = SpannableString(expandTagsone)
                //   tagspannable.setSpan(new ForegroundColorSpan(CYAN), 0, expandTagsone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tagspannable = CorpusUtilityorig.NewSetWordSpanTag(
                    word.tagone!!, word.tagtwo!!, word.tagthree!!,
                    word.tagfour!!, word.tagfive!!, " ", " ", "", "", expandTagsone
                )
            } else if (wordcount == 2) {
                val tagtwo: String = word.tagtwo!!
                val tagone: String = word.tagone!!
                var expandTagsone: String = QuranMorphologyDetails.Companion.expandTags(tagone)
                val tagnounone: Boolean = (tagone == "N") || (tagone == "ADJ") || (tagone == "PN")
                val tagnountwo: Boolean = (tagtwo == "N") || (tagtwo == "ADJ") || (tagtwo == "PN")
                var expandTagstwo: String = QuranMorphologyDetails.Companion.expandTags(tagtwo)
                if (tagnounone) {
                    expandTagsone = getNounDetails(word.detailsone)
                } else if (tagnountwo) {
                    expandTagstwo = getNounDetails(word.detailstwo)
                }
                if ((tagone == "V")) {
                    expandTagsone = getVerbDetails(expandTagsone, word.detailsone!!)
                } else if ((tagtwo == "V")) {
                    expandTagstwo = getVerbDetails(expandTagstwo, word.detailstwo!!)
                }
                tagspannable = CorpusUtilityorig.NewSetWordSpanTag(
                    word.tagone!!, word.tagtwo!!, word.tagthree!!,
                    word.tagfour!!, word.tagfive!!, " ", " ", "", expandTagstwo, expandTagsone
                )
            } else if (wordcount == 3) {
                val sb: StringBuilder  = StringBuilder()
                var expandTagsone: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagone!!)
                var expandTagstwo: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagtwo!!)
                var expandTagsthree: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagthree!!)
                val tagtwo: String = word.tagtwo!!
                val tagone: String = word.tagone!!
                val tagthree: String = word.tagthree!!
                val tagnounone: Boolean = (tagone == "N") || (tagone == "ADJ") || (tagone == "PN")
                val tagnountwo: Boolean = (tagtwo == "N") || (tagtwo == "ADJ") || (tagtwo == "PN")
                val tagnounthree: Boolean =
                    (tagthree == "N") || (tagthree == "ADJ") || (tagthree == "PN")
                if (tagnounone) {
                    expandTagsone = getNounDetails(word.detailsone)
                } else if (tagnountwo) {
                    expandTagstwo = getNounDetails(word.detailstwo)
                } else if (tagnounthree) {
                    expandTagsthree = getNounDetails(word.detailsthree)
                }
                if ((word.tagone == "V")) {
                    expandTagsone = getVerbDetails(expandTagsone, word.detailsone!!)
                } else if ((word.tagtwo == "V")) {
                    expandTagstwo = getVerbDetails(expandTagstwo, word.detailstwo!!)
                }
                if ((word.tagthree == "V")) {
                    expandTagsthree = getVerbDetails(expandTagsthree, word.detailsthree!!)
                }
                expandTagsone + " "
                expandTagstwo + " "
                expandTagsthree + " "
                sb.append(word.tagthree)
                sb.append("|")
                sb.append(word.tagtwo)
                sb.append("|")
                sb.append(word.tagone)
                tagspannable = CorpusUtilityorig.NewSetWordSpanTag(
                    word.tagone!!,
                    word.tagtwo!!,
                    word.tagthree!!,
                    word.tagfour!!,
                    word.tagfive!!,
                    " ",
                    " ",
                    expandTagsthree,
                    expandTagstwo,
                    expandTagsone
                )
            } else if (wordcount == 4) {
                val sb: StringBuilder  = java.lang.StringBuilder ()
                var expandTagsone: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagone!!)
                var expandTagstwo: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagtwo!!)
                var expandTagsthree: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagthree!!)
                var expandTagsfour: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagfour!!)
                val tagtwo: String = word.tagtwo!!
                val tagone: String = word.tagone!!
                val tagthree: String = word.tagthree!!
                val tagfour: String = word.tagfour!!
                val tagnounone: Boolean = (tagone == "N") || (tagone == "ADJ") || (tagone == "PN")
                val tagnountwo: Boolean = (tagtwo == "N") || (tagtwo == "ADJ") || (tagtwo == "PN")
                val tagnounthree: Boolean =
                    (tagthree == "N") || (tagthree == "ADJ") || (tagthree == "PN")
                val tagnounfour: Boolean =
                    (tagfour == "N") || (tagfour == "ADJ") || (tagfour == "PN")
                if (tagnounone) {
                    expandTagsone = getNounDetails(word.detailsone)
                    expandTagsone + " "
                } else if (tagnountwo) {
                    expandTagstwo = getNounDetails(word.detailstwo)
                    expandTagstwo + " "
                } else if (tagnounthree) {
                    expandTagsthree = getNounDetails(word.detailsthree)
                    expandTagsthree + " "
                } else if (tagnounfour) {
                    expandTagsfour = getNounDetails(word.detailsfour)
                }
                if ((word.tagone == "V")) {
                    expandTagsone = getVerbDetails(expandTagsone, word.detailsone!!)
                } else if ((word.tagtwo == "V")) {
                    expandTagstwo = getVerbDetails(expandTagstwo, word.detailstwo!!)
                } else if ((word.tagthree == "V")) {
                    expandTagsthree = getVerbDetails(expandTagsthree, word.detailsthree!!)
                } else if ((word.tagfour == "V")) {
                    expandTagsfour = getVerbDetails(expandTagsfour, word.detailsfour!!)
                }
                sb.append(word.tagfour!!)
                sb.append("|")
                sb.append(word.tagthree!!)
                sb.append("|")
                sb.append(word.tagtwo)
                sb.append("|")
                sb.append(word.tagone)
                tagspannable = CorpusUtilityorig.NewSetWordSpanTag(
                    word.tagone!!,
                    word.tagtwo!!,
                    word.tagthree!!,
                    word.tagfour!!,
                    word.tagfive!!,
                    " ",
                    expandTagsfour,
                    expandTagsthree,
                    expandTagstwo,
                    expandTagsone
                )
            } else if (wordcount == 5) {
                val sb: StringBuilder  = java.lang.StringBuilder()
                var expandTagsone: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagone!!)
                var expandTagstwo: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagtwo!!)
                var expandTagsthree: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagthree!!)
                var expandTagsfour: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagfour!!)
                var expandTagsfive: String =
                    QuranMorphologyDetails.Companion.expandTags(word.tagfive!!)
                if ((word.tagone == "V")) {
                    expandTagsone = getVerbDetails(expandTagsone, word.detailsone!!)
                } else if ((word.tagtwo == "V")) {
                    expandTagstwo = getVerbDetails(expandTagstwo, word.detailstwo!!)
                } else if ((word.tagthree == "V")) {
                    expandTagsthree = getVerbDetails(expandTagsthree, word.detailsthree!!)
                } else if ((word.tagfour == "V")) {
                    expandTagsfour = getVerbDetails(expandTagsfour, word.detailsfour!!)
                } else if ((word.tagfive == "V")) {
                    expandTagsfive = getVerbDetails(expandTagsfive, word.detailsfour!!)
                }
                sb.append(word.tagfour!!)
                sb.append("|")
                sb.append(word.tagthree!!)
                sb.append("|")
                sb.append(word.tagtwo!!)
                sb.append("|")
                sb.append(word.tagone!!)
                tagspannable = CorpusUtilityorig.NewSetWordSpanTag(
                    word.tagone!!,
                    word.tagtwo!!,
                    word.tagthree!!,
                    word.tagfour!!,
                    word.tagfive!!,
                    expandTagsone,
                    expandTagstwo,
                    expandTagsthree,
                    expandTagsfour,
                    expandTagsfive
                )
                sb.append("|")
                sb.append(word.tagfour!!)
                sb.append("|")
                sb.append(word.tagthree)
                sb.append("|")
                sb.append(word.tagtwo)
                sb.append("|")
                sb.append(word.tagone)
            }
            return tagspannable
        }

    private fun getNounDetails(wordetails: String?): String {
        var expandTagsone: String = ""
        val split: Array<String> =
            wordetails!!.split("\\|".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val wordbdetail: HashMap<String, SpannableStringBuilder?> = HashMap()
        val sb: StringBuilder  = java.lang.StringBuilder ()
        getNdetails(corpusNounWord, wordbdetail, sb)
        val genderNumberdetails1: String  =
            QuranMorphologyDetails.Companion.getGenderNumberdetails(
                corpusNounWord!!.get(0).gendernumber
            ).toString()
        if (wordbdetail.get("noun") != null) {
            expandTagsone = expandTagsone + wordbdetail.get("noun").toString()
            expandTagsone = expandTagsone + "Root:-" + corpusNounWord!!.get(0).root_a
        }
        //   expandTagsone = expandTagsone.concat(genderNumberdetails1.toString);
        return expandTagsone
    }

    private fun getVerbDetails(expandTagsone: String, wordetails: String): String {
        var expandTagsone: String = expandTagsone
        val split: Array<String> =
            wordetails.split("\\|".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val s: String = split.get(split.size - 1)
        if (!s.contains("MOOD")) {
            val genderNumberdetails: StringBuilder =
                QuranMorphologyDetails.Companion.getGenderNumberdetails(s)
            expandTagsone = expandTagsone + genderNumberdetails.toString()
            val verbTense: String = expandVerbTense(word.detailsone!!)
            expandTagsone = expandTagsone + "(" + verbTense + ")"
            expandTagsone = expandTagsone + verbfeaturesenglisharabic.IND
        } else {
            val ss: String = split.get(split.size - 2)
            val genderNumberdetails: StringBuilder =
                QuranMorphologyDetails.Companion.getGenderNumberdetails(ss)
            expandTagsone = expandTagsone + genderNumberdetails.toString()
            val verbTense: String = expandVerbTense(word.detailsone!!)
            expandTagsone = expandTagsone + "(" + verbTense + ")"
        }
        if (!s.contains("PASS")) {
            expandTagsone = expandTagsone + verbfeaturesenglisharabic.ACT
        } else {
            expandTagsone = expandTagsone + verbfeaturesenglisharabic.PASS
        }
        //   expandTagsone.concat(verbCorpusRootWord.get(0).getRoot_a);
        expandTagsone = expandTagsone + "Root:-" + verbCorpusRootWord!!.get(0).root_a
        return expandTagsone
    }

    private fun expandVerbTense(verbdetails: String): String {
        var tense: String
        if (verbdetails.contains("IMPF")) {
            tense = verbfeaturesenglisharabic.IMPF
        } else if (verbdetails.contains("IMPV")) {
            tense = verbfeaturesenglisharabic.IMPV
        } else if (verbdetails.contains("PERF")) {
            tense = verbfeaturesenglisharabic.PERF
        } else {
            tense = ""
        }
        if (verbdetails.contains("SUBJ")) {
            tense = tense + verbfeaturesenglisharabic.SUBJ
        } else if (verbdetails.contains("JUS")) {
            tense = tense + verbfeaturesenglisharabic.JUS
        }
        return tense
    }
}