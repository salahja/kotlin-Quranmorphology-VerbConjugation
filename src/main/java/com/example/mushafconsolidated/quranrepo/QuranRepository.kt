package com.example.mushafconsolidated.quranrepo






import androidx.lifecycle.LiveData
import database.Dao.kovDao
import database.Dao.mazeedDao
import database.Dao.mujarradDao
import database.entity.MazeedEntity
import database.entity.MujarradVerbs


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class QuranRepository(
    /* val buckwaterDao: BuckwaterDao,
     val quranVerbsDao: QuranVerbsDao,

     val verbcorpusDao: verbcorpusDao,
     val kovDao: kovDao,
    ,*/
    val mazeeddao: mazeedDao,
    val mujarradao: mujarradDao,
    val kovdao: kovDao,
 //   val quranicVerbsDao: QuranicVerbsDao,


    ) {

    val kov=  kovdao.getkovlive()
    val mujarradall=  mujarradao.getverbTriAlllive()

      val mazeedall=  mazeeddao.getMazeedAlllive()


    fun getMujarradroot(root : String): LiveData<List<MujarradVerbs>>
            =mujarradao.getverbTrilive(root)

    fun getMujarradWeakness(kov : String): LiveData<List<MujarradVerbs>>
            =mujarradao.getMujarradWeaknesslive(kov)
    suspend fun insertlive(entity : MujarradVerbs) {
        mujarradao.insertlive(entity)
    }



    fun getMazeedroot(root : String): LiveData<List<MazeedEntity>>
    =mazeeddao.getMazeedRootlive(root)

    fun getMazeedWeakness(kov : String): LiveData<List<MazeedEntity>>
            =mazeeddao.getMazeedWeaknesslive(kov)
    suspend fun insertlive(entity : MazeedEntity) {
        mazeeddao.insertlive(entity)
    }




}