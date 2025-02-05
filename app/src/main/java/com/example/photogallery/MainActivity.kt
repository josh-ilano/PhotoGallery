package com.example.photogallery

import android.graphics.Paint.Align
import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.photogallery.ui.theme.PhotoGalleryTheme

/**
 * Josh Ilano
 * Boston University
 * 2-4-25
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PhotoAlbum(Modifier.padding(innerPadding)) // wrapper function
                }
            }
        }
    }
}

// individual photo with a caption
@Composable
fun PhotoCard(imageId: Int, contentDescription: String, text: String, modifier: Modifier) {
    return Column(modifier = modifier) {
            Image(
                painter = painterResource(imageId),
                contentDescription = contentDescription,
                modifier = Modifier.size(150.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .border(BorderStroke(2.dp, Color.Black)))
            Text(
                text=text,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }

}

/**
 * This function takes in any amount of photo objects. (e.g. one row can have 2 images,
 * and another one can have 3. The weight will automatically calculate nevertheless
 */
@Composable
fun PhotoRow(vararg photoInfos: Triple <Int, String, String>) {
    Row (){ // ensure we are spaced evenly with a new modifier
        for (photoInfo in photoInfos) {
            val (imageId, contentDescription, text) = photoInfo
            PhotoCard(imageId, contentDescription, text, Modifier.weight(1f))
        }
    }
}


@Composable
fun PhotoAlbum(modifier: Modifier) {

    // all information regarding the photo, notice how they are 3-tuples so
    // we can unpack
    val photos = arrayOf(
        Triple(R.drawable.boston,
            "Boston",
            "View of the CDS"),
        Triple(R.drawable.coco,
            "Shih-tzu",
            "A happy senior Shih Tzu dog"),
        Triple(R.drawable.mit,
            "MIT",
            "View from an apartment owned by MIT"),
        Triple(R.drawable.marthas_vineyard,
        "Martha's Vineyard",
        "A collection of boats in Martha's Vineyard"),
        Triple(R.drawable.koda,
            "Goldendoodle",
            "A jolly Goldendoodle as a puppy"),
        Triple(R.drawable.church,
            "Seville Church",
            "Catedral de Sevilla en España")
    )


    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(text="Photo Album",
            fontWeight = FontWeight.Black,
            fontSize = 30.sp
        )
        // we make a 3x2 album
        PhotoRow(photos[0], photos[1])
        Spacer(modifier = Modifier.height(10.dp)) // spacers
        PhotoRow(photos[2], photos[3])
        Spacer(modifier = Modifier.height(10.dp))
        PhotoRow(photos[4], photos[5])
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhotoGalleryTheme {
//        Greeting("Android")
    }
}