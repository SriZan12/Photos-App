package com.example.photos_app.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photos_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    text: String,
) {
    Column(
        modifier = Modifier
            .background(color = Color.Green)
    )
    {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor= colorResource(id = R.color.purple_200)
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()

                    ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier.padding(4.dp),
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = text,
                                modifier = Modifier.padding(start = 8.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )


                        }
                    }


                }

            },
           /* navigationIcon = {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "icon",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        backPress?.onBackPressed()
                    })
            },*/
        )

    }
}