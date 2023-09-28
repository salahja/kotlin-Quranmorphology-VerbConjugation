package org.sj.verbConjugation.trilateral.unaugmented

import org.sj.verbConjugation.AmrNahiAmr
import org.sj.verbConjugation.FaelMafool
import org.sj.verbConjugation.IsmAlaZarfSagheer
import org.sj.verbConjugation.IsmZarf
import org.sj.verbConjugation.MadhiMudharay

open class ConjugationResult(
    var kov: Int, root: UnaugmentedTrilateralRoot, //13 conjugated verbs
    var originalResult: List<*>,
) {
    var root: UnaugmentedTrilateralRoot
        protected set

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<*>
    var madhiMudharay: MadhiMudharay = MadhiMudharay()
    var faelMafool: FaelMafool = FaelMafool()

    var ismala: IsmAlaZarfSagheer = IsmAlaZarfSagheer()
    var ismzarf: IsmZarf = IsmZarf()

        var amrandnahi: AmrNahiAmr= AmrNahiAmr()
        protected set

    init {
        originalResult = originalResult
        this.root = root
        finalResult = ArrayList(originalResult)


        val filterNotNull = originalResult.filterNotNull()


        if(filterNotNull.size==5){


             amrandnahi.anta= filterNotNull[0].toString()
            amrandnahi.antuma= filterNotNull[2].toString()
            amrandnahi.antum= filterNotNull[3].toString()
            amrandnahi.anti= filterNotNull[1].toString()
            amrandnahi.antumaf= filterNotNull[2].toString()
            amrandnahi.antunna= filterNotNull[4].toString()

        }

      else  if(originalResult.size==13) {
            val toString = finalResult.toString()
            val split = toString.split(",")




            madhiMudharay.hua = split[0]
            madhiMudharay.huma = split[1]
            madhiMudharay.hum = split[2]
            madhiMudharay.hia = split[3]
            madhiMudharay.humaf = split[4]
            madhiMudharay.hunna = split[5]
            madhiMudharay.anta = split[6]
            madhiMudharay.antuma = split[7]
            madhiMudharay.antum = split[8]
            madhiMudharay.anti = split[9]
            madhiMudharay.antumaf = split[7]
            madhiMudharay.antunna = split[10]
            madhiMudharay.ana = split[11]
            madhiMudharay.nahnu = split[12]








        }else if(originalResult.size==18  && originalResult[0].toString().isNotEmpty()){
            val toString = finalResult.toString()
            val split = toString.split(",")
            faelMafool.nomsinM = split[0]//sinM
            faelMafool.nomdualM  = split[2]//dualM
            faelMafool.nomplurarM  = split[4]//plurarM
            faelMafool.accsinM  = split[6]//sinM
            faelMafool.accdualM  = split[8]//dualM
            faelMafool.accplurarlM  = split[10]//plurarlM
            faelMafool.gensinM  = split[12]//sinM
            faelMafool.gendualM  = split[14]//dualM
            faelMafool.genplurarM  = split[16]//plurarM


            faelMafool. nomsinF  = split[1]//sinF
            faelMafool. nomdualF  = split[3]//dualF
            faelMafool. nomplurarF  = split[5]//plurarF
            faelMafool. accsinF  = split[7]//sinF
            faelMafool. accdualF  = split[9]//dualF
            faelMafool. accplurarlF  = split[11]//plurarlF
            faelMafool. gensinF  = split[13]//sinF
            faelMafool. gendualF  = split[15]//dualF
            faelMafool. genplurarF  = split[17]//plurarF

        }
        else if(originalResult.size==18  && originalResult[0].toString().isEmpty()) {

            val toString = finalResult.toString()
            val split = toString.split(",")
            //1,3,7,9,13,14
            faelMafool.nomsinM = split[0]//sinM
            faelMafool.nomdualM = split[2]//dualM
            faelMafool.nomplurarM = split[4]//plurarM
            faelMafool.accsinM = split[6]//sinM
            faelMafool.accdualM = split[8]//dualM
            faelMafool.accplurarlM = split[10]//plurarlM
            faelMafool.gensinM = split[12]//sinM
            faelMafool.gendualM = split[14]//dualM
            faelMafool.genplurarM = split[16]//plurarM
            faelMafool.nomsinF = split[1]//sinF
            faelMafool.nomdualF = split[3]//dualF
            faelMafool.nomplurarF = split[5]//plurarF
            faelMafool.accsinF = split[7]//sinF
            faelMafool.accdualF = split[9]//dualF
            faelMafool.accplurarlF = split[11]//plurarlF
            faelMafool.gensinF = split[13]//sinF
            faelMafool.gendualF = split[15]//dualF
            faelMafool.genplurarF = split[17]
        }


    }
}

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: يمثل نتيجة التصريف مع الجذر ونوع الجذر
 * يستعمل في المعالجة بعد التوليد
 *
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */


/*
open class ConjugationResult(
    kov: Int,
    root: UnaugmentedTrilateralRoot,
    originalResult:List<*>,
) {
    var kov: Int
    lateinit var root: UnaugmentedTrilateralRoot

    //13 conjugated verbs
    var originalResult: MutableList<Any>
  //  val finalResult: MutableList<Any>
    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<Any>

    init {
        this.kov = kov
        this.originalResult = originalResult as MutableList<Any>
        this.root = root!!
        finalResult = ArrayList(originalResult)
    }






    override fun toString(): String {
        return "ConjugationResult{" +
                "kov=" + kov +
                ", root!!=" + root!! +
                ", originalResult=" + originalResult +
                ", finalResult=" + finalResult +
                '}'
    }
}

 */