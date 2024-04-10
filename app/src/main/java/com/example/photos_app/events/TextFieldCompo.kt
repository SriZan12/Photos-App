package com.example.photos_app.events

import com.example.photos_app.R
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OutlinedTextFieldCompo(
    modifier: Modifier,
    placeholderText: String,
    value: String,
    imageVector: ImageVector,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions { },
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    enable: Boolean = true,
    leadingIcon: Boolean = false,
    onClickLeadingIcon: () -> Unit
) {

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChanged(it) },
        placeholder = { Text(text = placeholderText) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardCapitalization,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        leadingIcon = {
            if (leadingIcon) {
                IconButton(onClick = { onClickLeadingIcon() }) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "icon",
                        tint = Color.Gray
                    )
                }
            }
        },
        keyboardActions = keyboardActions,
        enabled = enable,
        colors = TextFieldDefaults.colors(
            focusedPlaceholderColor = colorResource(id = R.color.purple_200),
            focusedTextColor = Color.Black,
            focusedContainerColor = Color.White
        ),
    )

}

