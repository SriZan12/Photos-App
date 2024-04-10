package com.example.photos_app.features

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photos_app.R

@Composable
fun TriangularImageScreen(homeViewModel: HomeViewModel) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TriangularImageUI(homeViewModel = homeViewModel)
        }
}

@Composable
fun TriangularImageUI(homeViewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        ImageList(
            pictureList = homeViewModel.imageList.collectAsState().value!!,
            sizes = homeViewModel.size.collectAsState().value,
            indexes = homeViewModel.indexes.collectAsState().value!!
        )
    }
}

@Composable
fun ImageList(pictureList: List<Uri>, sizes: String, indexes: Set<Int>) {

    val totalItems = pictureList.size
    var count = 0
    var x = 0
    val size = sizes.toInt()
    for (i in 0 until size) {
        Row(
            Modifier
                .horizontalScroll(rememberScrollState())
                .padding(4.dp)
        ) {
            // show images
            for (k in 0 until i + 1) {
                if (count in indexes && count == x) {
                    ItemImage(
                        count = count,
                        uri = pictureList[count],
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                if (count !in indexes && count == x) {
                    ItemImage(
                        count = count,
                        uri = pictureList[count],
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                if (count < totalItems - 1) {
                    count++
                }
                x++
            }

        }

    }


}

@Composable
fun ItemImage(count: Int, uri: Uri) {

    Box(
        modifier = Modifier
            .size(46.dp)
            .border(1.dp, color = colorResource(id = R.color.purple_200), RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = (uri),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .align(Alignment.Center)
        )
        Text(
            text = "$count",
            modifier = Modifier
                .align(Alignment.TopEnd),
            color = Color.Black
        )
    }
}