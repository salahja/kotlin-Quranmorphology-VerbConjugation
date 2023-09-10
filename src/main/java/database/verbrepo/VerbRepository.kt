package database.verbrepo






import androidx.lifecycle.LiveData
import database.Dao.mazeedDao
import database.Dao.mujarradDao
import database.entity.MazeedEntity
import database.entity.MujarradVerbs


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class VerbRepository(
   /* val buckwaterDao: BuckwaterDao,
    val quranVerbsDao: QuranVerbsDao,
    val quranicVerbsDao: QuranicVerbsDao,
    val verbcorpusDao: verbcorpusDao,
    val kovDao: kovDao,
   ,*/
    val mazeeddao: mazeedDao,
   val mujarradao: mujarradDao



) {
/*
  @Query(value = "SELECT * FROM mujarrad where root=:root")
    fun getverbTrilive(root: String?): LiveData<List<MujarradVerbs>>



    @Query(value = "select DISTINCT root,bab,babname ,verbtype,kov,kovname,id,verb from mujarrad where kov=:kov order by root limit 30")
    fun getMujarradWeaknesslive(kov: String?): LiveData<List<MujarradVerbs>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertlive(entity: MujarradVerbs?): Long

 */


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