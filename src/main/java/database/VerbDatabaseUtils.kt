package database

import android.content.Context
import database.entity.Mazeed
import database.entity.MujarradVerbs
import database.entity.QuranVerbsEntity
import database.entity.QuranicVerbsEntity
import database.entity.kov
import database.entity.verbcorpus

class VerbDatabaseUtils(context: Context?) {
    // {
    //     LiveData<List<wordbyword>> liveUsers = WordbywordPojoDao.getWords(new
    //             SimpleSQLiteQuery("SELECT * FROM "));
    // }
    init {
        database = VerbDatabase.getInstance(context)!!

    }

    //  public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    //   @Override
    //    public void migrate(SupportSQLiteDatabase database) {
    //       database.execSQL("CREATE TABLE IF NOT EXISTS `quranicverbs` (`verb` TEXT, `root` TEXT, `thulathibab` TEXT, `form` INTEGER NOT NULL, `chaptername` TEXT, `frequency` INTEGER NOT NULL, `meaning` TEXT, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
    //  }
    //  };
    val kov: List<kov?>?
        get() = VerbDatabaseUtils.Companion.database.kovDao()?.rules

    fun getQuranVerbsbyFrequency(sort: Int): List<QuranVerbsEntity?>? {
        return VerbDatabaseUtils.Companion.database.QuranVerbsDao()
            ?.getverbsbyFrequency(sort)
    }

    val quranicVerbsMazeed: ArrayList<QuranicVerbsEntity>
        get() = VerbDatabaseUtils.Companion.database.QuranicVerbsDao()
            ?.getverbsMazeed() as ArrayList<QuranicVerbsEntity>
    val quranicVerbsbyForm: ArrayList<QuranicVerbsEntity>
        get() = VerbDatabaseUtils.Companion.database.QuranicVerbsDao()
            ?.getverbsbyForm() as ArrayList<QuranicVerbsEntity>

    fun updateTrimRoots(nroot: String?, id: Int): Int {
        return VerbDatabaseUtils.Companion.database.QuranicVerbsDao()!!.updadateRoots(nroot, id)
    }

    fun updateThulathibab(nroot: String?, id: Int): Int {
        return VerbDatabaseUtils.Companion.database.QuranicVerbsDao()!!.updadateThulathibab(nroot, id)
    }

    fun getMujarradVerbs(root: String?): List<MujarradVerbs?>? {
        return VerbDatabaseUtils.Companion.database.mujarradDao()
            ?.getverbTri(root)
    }

    val mujarradAall: ArrayList<MujarradVerbs>
        get() = VerbDatabaseUtils.Companion.database.mujarradDao()
            ?.getverbTriAll() as ArrayList<MujarradVerbs>

    fun getMujarradBYWeakness(kov: String?): List<MujarradVerbs?>? {
        return VerbDatabaseUtils.Companion.database.mujarradDao()
            ?.getMujarradWeakness(kov)
    }

    fun getMazeedWeakness(kov: String?): List<Mazeed?>? {
        return VerbDatabaseUtils.Companion.database.mazeedDao()
            ?.getMazeedWeakness(kov)
    }

    fun getMazeedRoot(root: String?): List<Mazeed?>? {
        return VerbDatabaseUtils.Companion.database.mazeedDao()
            ?.getMazeedRoot(root)
    }

    fun verbcorpuses(): List<verbcorpus?>? {
        return VerbDatabaseUtils.Companion.database.verbcorpusDao()?.getmazeedform("I")
    }

    companion object {
        private var database: VerbDatabase = TODO()
    }
}