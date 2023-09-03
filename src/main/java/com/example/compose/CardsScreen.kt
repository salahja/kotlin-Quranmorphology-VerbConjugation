

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.preference.PreferenceManager
import com.codelab.basics.ui.theme.cardCollapsedBackgroundColor
import com.codelab.basics.ui.theme.cardExpandedBackgroundColor
import com.codelab.basics.ui.theme.qalam
import com.example.compose.CardsViewModel
import com.example.compose.ExpandableCardModel
import com.example.compose.LemmaViewModel
import com.example.compose.VerseOccuranceModel
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication.Companion.context
import com.skyyo.expandablelist.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi


var lemmarabic: String?=null
var words: List<VerseOccuranceModel>? = null
val EXPANSTION_TRANSITION_DURATION = 300

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun CardsScreen(viewModel: CardsViewModel) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
        context!!
    )


    //grammatically colred word default font
    val arabic_font_selection =
        sharedPreferences.getString("Arabic_Font_Selection", "quranicfontregular.ttf")
   // val words by wordoccuranceModel.words.collectAsStateWithLifecycle()
    val cards by viewModel.cards.collectAsStateWithLifecycle()
    val expandedCardIds by viewModel.expandedCardIdsList.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    viewModel.open.value=true
    LoadingDialog()

    val bgColour = remember {
        Color(ContextCompat.getColor(context, R.color.colorDayNightWhite))
    }

    Scaffold(Modifier.background(bgColour)) { paddingValues ->
        val copyProgress: MutableState<Float> = remember { mutableStateOf(0.0f) }
      //  CustomLinearProgressBar()
        LazyColumn(Modifier.padding(paddingValues)) {
            items(cards, ExpandableCardModel::id) { card ->
                ExpandableCard(
                    card = card,
                    onCardArrowClick = { viewModel.onCardArrowClicked(card.id) },
                    expanded = expandedCardIds.contains(card.id),
                )
            }
            viewModel.open.value=false

        }
/*

     TopAppBar(
            title = {
                Text(text = "Word Occurance" )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                  //  Icon(imageVector = Icons.Default.AddLink, contentDescription = "")
                    Icon(Icons.Rounded.ArrowBack, "")
                }
            },
       //  backgroundColor = Color.Transparent,
           //    contentColor = Color.Gray,
          // elevation = 2.dp
        )

*/




    }
}
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomLinearProgressBar()
/*    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }*/
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoadingDialog(
    cornerRadius: Dp = 16.dp,
    progressIndicatorColor: Color = Color(0xFF35898f),
    progressIndicatorSize: Dp = 80.dp
) {
    val viewModel: CardsViewModel = viewModel()

    val showDialog by viewModel.open.observeAsState(initial = true) // initially, don't show the dialog

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false // disable the default size so that we can customize it
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 42.dp, end = 42.dp) // margin
                    .background(color = Color.White, shape = RoundedCornerShape(cornerRadius))
                    .padding(top = 36.dp, bottom = 36.dp), // inner padding
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ProgressIndicatorLoading(
                    progressIndicatorSize = progressIndicatorSize,
                    progressIndicatorColor = progressIndicatorColor
                )

                // Gap between progress indicator and text
                Spacer(modifier = Modifier.height(32.dp))

                // Please wait text
                Text(
                    text = "Please wait...",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }

    Button(
        onClick = {
            viewModel.open.value = true
       //  viewModel.startThread()
        }
    ) {
        Text(text = "Show Loading Dialog")
    }
}

@Composable
private fun ProgressIndicatorLoading(
    progressIndicatorSize: Dp,
    progressIndicatorColor: Color
) {
    val infiniteTransition = rememberInfiniteTransition()

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 600
            }
        )
    )

    CircularProgressIndicator(
        progress = 1f,
        modifier = Modifier
            .size(progressIndicatorSize)
            .rotate(angle)
            .border(
                12.dp,
                brush = Brush.sweepGradient(
                    listOf(
                        Color.White, // add background color first
                        progressIndicatorColor.copy(alpha = 0.1f),
                        progressIndicatorColor
                    )
                ),
                shape = CircleShape
            ),
        strokeWidth = 1.dp,
        color = Color.White // Set background color
    )
}






@SuppressLint("UnusedTransitionTargetStateParameter")

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        CustomLinearProgressBar();
    }
}

@Composable
private fun CustomLinearProgressBar(){
    Column(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
           // backgroundColor = Color.LightGray,
            color = Color.Red //progress color
        )
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(



card: ExpandableCardModel,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition")
    val cardBgColor by transition.animateColor({

        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "bgColorTransition") {
        if (expanded) cardExpandedBackgroundColor else cardCollapsedBackgroundColor
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "paddingTransition") {
        if (expanded) 48.dp else 24.dp
    }
    val cardElevation: Dp by transition.animateDp({
        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "elevationTransition") {
        if (expanded) 24.dp else 4.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = EXPANSTION_TRANSITION_DURATION,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        if (expanded) 0.dp else 16.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPANSTION_TRANSITION_DURATION)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }
    val context = LocalContext.current
    val contentColour = remember {
        Color(ContextCompat.getColor(context, R.color.colorDayNightPurple))
    }

    Card(
        //  backgroundColor = cardBgColor,
        //  contentColor = contentColour,
        //     elevation = cardElevation,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
            )
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(),

         ){
            Box {
                CardArrow(
                    degrees = arrowRotationDegree,
                    onClick = onCardArrowClick
                )
                CardTitle(title = card.title)
            }

            lemmarabic=card.lemma
         //   MyScreen(visible = expanded)
            ExpandableContent(card.lemma,card.vlist, visible = expanded)
            //     ExpandableContent( visible = expanded)
        }
    }
}

@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun CardArrow(
    degrees: Float,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_less_24),
                contentDescription = "Expandable Arrow",
                modifier = Modifier.rotate(degrees),
            )
        }
    )
}
@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = Color.Cyan,
    backgroundColor: Color = Color.Black,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}

class MyViewModel : ViewModel() { /*...*/ }

// import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun MyScreen(
    visible: Boolean = true,
    viewModel: LemmaViewModel = viewModel()
) {

     viewModel.loadLists(lemmarabic!!)


}
@Composable
fun CenterAlignedText() {
    Text(
        text = "Center",
        textAlign = TextAlign.Center,
        modifier = Modifier.size(100.dp)
            .background(Color.Cyan)
            .wrapContentHeight(),
    )
}


@Composable
fun ExpandableContent(


    lemma:String,
    card: ArrayList<String>,
    visible: Boolean = true,
//    viewModel: LemmaViewModel = viewModel(),




//  viewModel: Any = viewModel<LemmaViewModel>()

) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        )
    }

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(modifier = Modifier.padding(8.dp))


        {
            println(lemma)
            card.forEach { verses ->

                Text(
                    text = verses, style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Light,
                        textDirection = TextDirection.ContentOrRtl,
                        textAlign = TextAlign.Center,
                        fontFamily = qalam

                    )


                )

                // on below line we are specifying
                // divider for each list item
                Divider(color = Color.Red, thickness = 3.dp)
            }
            Divider()
        }

        /*
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.heightIn(100.dp))
            Text(
           //  text =card.verseslist.get(0),
                text=card.get(0),
             //   text="expan",
                textAlign = TextAlign.Center
            )

        }*/
    }



}
