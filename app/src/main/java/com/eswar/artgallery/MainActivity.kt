package com.eswar.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eswar.artgallery.ui.theme.ArtGalleryTheme
import kotlin.collections.listOf

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val arts = listOf(
      Art(
        artId = R.drawable.shakuntala_by_raja_ravi_varma,
        title = getString(R.string.shakuntala),
        artist = getString(R.string.raja_ravi_varma),
        year = 1898
      ),
      Art(
        artId = R.drawable.bharat_mata_by_abanindranath_tagore,
        title = getString(R.string.bharat_mata),
        artist = getString(R.string.abanindranath_tagore),
        year = 1905
      ),
      Art(
        artId = R.drawable.magnolia_and_erect_rock_chen_hongshou,
        title = getString(R.string.magnolia_and_erect_rock),
        artist = getString(R.string.chen_hongshou),
        year = null
      ),
      Art(
        artId = R.drawable.mona_lisa_by_leonardo_da_vinci,
        title = getString(R.string.mona_lisa),
        artist = getString(R.string.leonardo_da_vinci),
        year = null
      ),
      Art(
        artId = R.drawable.nighthawks_by_edward_hopper_1942,
        title = getString(R.string.night_hawks),
        artist = getString(R.string.edward_hopper),
        year = 1942
      ),
      Art(
        artId = R.drawable.starry_night_by_van_gogh,
        title = getString(R.string.starry_night),
        artist = getString(R.string.vincent_van_gogh),
        year = 1889
      ),
      Art(
        artId = R.drawable.basket_of_fruit_by_caravaggio,
        title = getString(R.string.basket_of_fruit),
        artist = getString(R.string.caravaggio),
        year = 1599
      ),
      Art(
        artId = R.drawable.the_japanese_footbridge_and_the_water_lily_pool_by_claude_monet,
        title = getString(R.string.the_japanese_footbridge_and_the_water_lily_pool),
        artist = getString(R.string.claude_monet),
        year = 1899
      ),
      Art(
        artId = R.drawable.the_scream_by_edvard_munch_1893,
        title = getString(R.string.the_scream),
        artist = getString(R.string.edvard_munch),
        year = 1893
      ),
      Art(
        artId = R.drawable.the_england_scenery_by_frederic_edwin_church_1851,
        title = getString(R.string.the_england_scenery),
        artist = getString(R.string.frederic_edwin_church),
        year = 1851
      ),
    ).shuffled()
    setContent {
      ArtGalleryTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          App(
            modifier = Modifier.padding(innerPadding),
            arts
          )
        }
      }
    }
  }
}

data class Art(
  @DrawableRes val artId: Int,
  val title: String,
  val artist: String,
  val year: Int?
) {
  override fun toString(): String {
    return "${this.title} by ${this.artist}"
  }
}

@Composable
fun App(modifier: Modifier = Modifier, arts: List<Art>) {
  var currentArt by remember { mutableIntStateOf(0) }

  Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    Spacer(Modifier)
    ArtImage(arts[currentArt].artId)

    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      ArtDetails(arts[currentArt].title, arts[currentArt].artist, arts[currentArt].year)
      Spacer(Modifier.height(32.dp))

      Row(
        modifier = Modifier
          .padding(start = 20.dp, end = 20.dp)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Button(
          onClick = {
            currentArt = (currentArt - 1 + arts.size) % arts.size
          }
        ) {
          Text("Previous")
        }

        Button(
          onClick = {
            currentArt = (currentArt + 1) % arts.size
          }
        ) {
          Text("Next")
        }
      }
      Spacer(Modifier.height(32.dp))
    }
  }
}

@Composable
fun ArtImage(@DrawableRes drawableId: Int) {
  Box(
    modifier = Modifier
      .fillMaxWidth(.9f)
      .fillMaxHeight(.5f)
      .shadow(8.dp)
      .background(Color(0xFFFFFFFF)),
    contentAlignment = Alignment.Center
  ) {
    Crossfade(targetState = drawableId, label = "Art transition") { targetDrawableId ->
      Image(
        painter = painterResource(targetDrawableId),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
          .padding(16.dp)
          .fillMaxSize(),
      )
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtDetails(title: String, artist: String, year: Int?) {
  Column(
    modifier = Modifier
      .background(Color.LightGray)
      .padding(16.dp)
      .fillMaxWidth(.9f)
  ) {
    Text(
      text = title,
      fontSize = 28.sp,
      fontWeight = FontWeight.Light,
      modifier = Modifier.basicMarquee(Int.MAX_VALUE)
    )

    val artistAndYear = buildAnnotatedString {
      pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
      append("$artist ")
      pop()
      if (year != null) {
        pushStyle(SpanStyle(fontWeight = FontWeight.ExtraLight))
        append("($year)")
        pop()
      }
    }

    Text(
      text = artistAndYear
    )
  }
}

@Preview
@Composable
private fun AppPreview() {
  ArtGalleryTheme {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
      App(
        modifier = Modifier.padding(innerPadding),
        listOf(
          Art(
            artId = R.drawable.shakuntala_by_raja_ravi_varma,
            title = "Shakuntala",
            artist = "Raja Ravi Varma",
            year = 1898
          ),
          Art(
            artId = R.drawable.bharat_mata_by_abanindranath_tagore,
            title = "Bharat Mata",
            artist = "Abanindranath Tagore",
            year = 1905
          ),
          Art(
            artId = R.drawable.magnolia_and_erect_rock_chen_hongshou,
            title = "Magnolia and Erect Rock",
            artist = "Chen Hongshou",
            year = null
          )
        )
      )
    }
  }
}