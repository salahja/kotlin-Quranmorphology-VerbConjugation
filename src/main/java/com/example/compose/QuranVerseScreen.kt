package com.example.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codelab.basics.ui.theme.qalam
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.model.NewQuranCorpusWbw
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication
@Composable
fun QuranVerseScreen(navController: NavHostController, chapid: Int) {
    var allofQuran: List<QuranEntity>? = null

    val imgs = QuranGrammarApplication.context!!.resources.obtainTypedArray(R.array.sura_imgs)
    val utils = Utils(QuranGrammarApplication.context)
    val allAnaChapters = utils.getAllAnaChapters()!!;
    val quranbySurah = utils.getQuranbySurah(1)
     var newnewadapterlist = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
     var corpusSurahWord: List<QuranCorpusWbw>? = null

val corpus=CorpusUtilityorig
    corpusSurahWord = utils.getQuranCorpusWbwbysurah(1);

    newnewadapterlist = corpus.composeWBWCollection(quranbySurah, corpusSurahWord)

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(1),
        content = {
            items(quranbySurah!!.size) { index ->
                VerseList(quranbySurah!![index] ,navController,newnewadapterlist[index] )






            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun VerseList(
    quran: QuranEntity,

    navController: NavHostController,
    wbw: ArrayList<NewQuranCorpusWbw>?
) {
    Card(

        //   onCardArrowClick = { viewModel.onCardArrowClicked(card.id) },
        /*       onClick = { surahModelList.chapterid

                   navController.navigate(NavigationItem.Music.route)


               },*/
        onClick = {

            navController.navigate(
                NavigationItem.Music.route
                    //   "detail/{uId}" //Just modify your route accordingly
                    .replace(
                        oldValue = "{uId}",
                        newValue = "1"
                    )
            )
        },





        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),


        modifier = Modifier
            .fillMaxWidth()

            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            )
    )


    {
       FlowRow(



           verticalArrangement = Arrangement.Top,
           horizontalArrangement = Arrangement.SpaceEvenly,

           modifier = Modifier
               .fillMaxWidth()

               .padding(
                   horizontal = 10.dp,
                   vertical = 8.dp
               )


       ) {
           if (wbw != null) {
           //    var counter=wbw.size
               for (counter in wbw.size-1 downTo 0) {





              val str =
                  wbw!![counter].corpus!!.araone.toString() + wbw!![counter].corpus!!.aratwo.toString()+
                   wbw!![counter].corpus!!.arathree.toString()+wbw!![counter].corpus!!.arafour.toString()+
                               wbw!![counter].corpus!!.arafive.toString()+"\n"+wbw[counter].wbw?.en.toString()






                 Text(
                       text = str, style = MaterialTheme.typography.headlineSmall.copy(
                           fontWeight = FontWeight.Light,
                           textDirection = TextDirection.Ltr,
                           textAlign = TextAlign.Center,
                           fontFamily = qalam

                       )
                 )








/*


                   Text(
                       text = strs, style = MaterialTheme.typography.headlineSmall.copy(
                           fontWeight = FontWeight.Light,
                           textDirection = TextDirection.Ltr,
                           textAlign = TextAlign.Center,
                           fontFamily = qalam

                       )
                   )
*/








               }
           }











       }
        Row (
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()

                .padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                )

        ){
            Text(

                text = quran!!.qurantext.toString(),
                fontSize = 20.sp
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()

                .padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                )
        ){

            Text(

                text = quran!!.translation,
                fontSize = 20.sp
            )

        }







    }


}
