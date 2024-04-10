package com.example.photos_app.features

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.photos_app.R
import com.example.photos_app.components.CustomButton
import com.example.photos_app.components.Toolbar
import com.example.photos_app.events.ImageEvent
import com.example.photos_app.events.OutlinedTextFieldCompo
import com.example.photos_app.utils.PermissionUtils

@Composable
fun HomeScreen(context: Context, homeViewModel: HomeViewModel = viewModel()) {

    Init(context = context)

    ImageScreenContent(context = context, homeViewModel = homeViewModel)
}

@Composable
fun Init(context: Context) {
    LaunchedEffect(Unit) {
        PermissionUtils.askForReadExternalStoragePermissions(context)
    }
}

@Composable
fun ImageScreenContent(context: Context, homeViewModel: HomeViewModel) {
    Scaffold(
        topBar = {
            Toolbar(stringResource(R.string.photos_app))
        },
        bottomBar = {
            if (homeViewModel.imageList.collectAsState().value != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp)
                        .navigationBarsPadding()
                ) {
                    CustomButton(
                        onClick = { homeViewModel.onEvent(event = ImageEvent.OnClearSize) },
                        buttonText = stringResource(id = R.string.clear)
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (homeViewModel.imageList.collectAsState().value != null) {
                TriangularImageScreen(homeViewModel = homeViewModel)
            } else {
                ImagePickerUI(context = context, onEvent = { event ->
                    homeViewModel.onEvent(event = event)
                }, homeViewModel = homeViewModel)
            }

        }
    }
}

@Composable
fun ImagePickerUI(context: Context, onEvent: (ImageEvent) -> Unit, homeViewModel: HomeViewModel) {
    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents(),
            onResult = { uri ->
                if (uri.size == 2) {
                    onEvent(ImageEvent.OnPickImage(uri))
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.please_select_two_image_at_a_time),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })


    Card(
        modifier = Modifier
            .wrapContentSize(align = Alignment.Center)
            .padding(all = 8.dp)
            .border(
                1.dp,
                color = colorResource(id = R.color.purple_200),
                RoundedCornerShape(4.dp)
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.add_number_of_image_list_size),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextFieldCompo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholderText = stringResource(R.string.type_size_of_list),
                value = homeViewModel.size.collectAsState().value,
                imageVector = Icons.Filled.AddCircle,
                onValueChanged = { listSize ->
                    if (listSize.isDigitsOnly()) {
                        onEvent(ImageEvent.OnSizeChange(listSize))
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.please_select_only_number),
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                onClickLeadingIcon = {}

            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomButton(onClick = {
                if (homeViewModel.size.value.isEmpty()) {
                    onEvent(ImageEvent.OnSizeChange("50"))
                } else {
                    imagePickerLauncher.launch("image/*")

                }
            }, buttonText = stringResource(id = R.string.generate))
        }
    }


}


