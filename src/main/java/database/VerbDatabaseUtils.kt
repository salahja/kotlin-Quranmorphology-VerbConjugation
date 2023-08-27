package database

import android.content.Context
import database.entity.MazeedEntity
import database.entity.MujarradVerbs
import database.entity.QuranVerbsEntity
import database.entity.QuranicVerbsEntity
import database.entity.kov
import database.entity.verbcorpus

class VerbDatabaseUtils(context: Context?) {
    init {

        database = VerbDatabase.getInstance(context)!!
    }

    val kov: ArrayList<kov?>?
        get() = database.kovDao()!!.rules as ArrayList<kov?>?

    fun getQuranVerbsbyFrequency(sort: Int): ArrayList<QuranVerbsEntity?>? {
        return database.QuranVerbsDao()!!.getverbsbyFrequency(sort) as ArrayList<QuranVerbsEntity?>?
    }

    val quranicVerbsMazeed: ArrayList<QuranicVerbsEntity?>?
        get() = database.QuranicVerbsDao()!!.getverbsMazeed() as ArrayList<QuranicVerbsEntity?>?
    val quranicVerbsbyForm: ArrayList<QuranicVerbsEntity?>?
        get() = database.QuranicVerbsDao()!!.getverbsbyForm() as ArrayList<QuranicVerbsEntity?>?

    fun updateTrimRoots(nroot: String?, id: Int): Int {
        return database.QuranicVerbsDao()!!.updadateRoots(nroot, id)
    }

    fun updateThulathibab(nroot: String?, id: Int): Int {
        return database.QuranicVerbsDao()!!
            .updadateThulathibab(nroot, id)
    }

    fun getMujarradVerbs(root: String?): ArrayList<MujarradVerbs?>? {
        return database.mujarradDao()!!.getverbTri(root) as ArrayList<MujarradVerbs?>?
    }

    val mujarradAall: ArrayList<MujarradVerbs?>?
        get() = database.mujarradDao()!!.getverbTriAll() as ArrayList<MujarradVerbs?>?

    fun getMujarradBYWeakness(kov: String?): ArrayList<MujarradVerbs?>? {
        return database.mujarradDao()!!.getMujarradWeakness(kov) as ArrayList<MujarradVerbs?>?
    }

    fun getMazeedWeakness(kov: String?): ArrayList<MazeedEntity?>? {
        return database.mazeedDao()!!.getMazeedWeakness(kov) as ArrayList<MazeedEntity?>?
    }

    fun getMazeedRoot(root: String?): ArrayList<MazeedEntity?>? {
        return database.mazeedDao()!!.getMazeedRoot(root) as ArrayList<MazeedEntity?>?
    }

    fun verbcorpuses(): ArrayList<verbcorpus?>? {
        return database.verbcorpusDao()!!.getmazeedform("I") as ArrayList<verbcorpus?>?
    }

    companion object {
        private lateinit var database: VerbDatabase
    }
}








