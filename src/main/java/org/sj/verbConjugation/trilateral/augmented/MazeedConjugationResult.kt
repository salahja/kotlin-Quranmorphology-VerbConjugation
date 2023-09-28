package org.sj.verbConjugation.trilateral.augmented

import org.sj.verbConjugation.AmrNahiAmr
import org.sj.verbConjugation.FaelMafool
import org.sj.verbConjugation.MadhiMudharay

class MazeedConjugationResult {

    val kov: Int
    val formulaNo: Int
    var originalResult: List<*>
    var root: AugmentedTrilateralRoot
        protected set

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<*>
    var madhiMudharay: MadhiMudharay = MadhiMudharay()
    var faelMafool: FaelMafool = FaelMafool()
    var amrandnahi: AmrNahiAmr = AmrNahiAmr()
        protected set
    //13 conjugated verbs
    constructor(kov: Int, formulaNo: Int, root: AugmentedTrilateralRoot, originalResult: List<*>) {
        this.kov = kov
        this.formulaNo = formulaNo
        this.originalResult = originalResult

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
        }else if(originalResult.size==18){
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





    }


}