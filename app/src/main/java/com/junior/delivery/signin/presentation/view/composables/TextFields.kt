package com.junior.delivery.signin.presentation.view.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.junior.delivery.R
import com.junior.delivery.ui.theme.UltraPurple

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BasicTextField(
    value: String,
    placeholder: String,
    label: String,
    onTextChanged: (String) -> Unit,
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = UltraPurple,
        focusedLeadingIconColor = UltraPurple,
        focusedSupportingTextColor = UltraPurple,
        unfocusedLabelColor = UltraPurple,
        unfocusedLeadingIconColor = UltraPurple,
        unfocusedSupportingTextColor = UltraPurple,
        containerColor = Color.White,
        placeholderColor = Color.Black,
        textColor = Color.Black
    )

    TextField(
        value = value,
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        onValueChange = { onTextChanged(it) },
        colors = textFieldColors,
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, UltraPurple),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = label)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val textFieldColors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = UltraPurple,
        focusedLeadingIconColor = UltraPurple,
        focusedSupportingTextColor = UltraPurple,
        unfocusedLabelColor = UltraPurple,
        unfocusedLeadingIconColor = UltraPurple,
        unfocusedSupportingTextColor = UltraPurple,
        containerColor = Color.White,
        placeholderColor = Color.Black,
        textColor = Color.Black
    )

    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        colors = textFieldColors,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, UltraPurple),
        label = { Text(stringResource(id = R.string.password)) },
        placeholder = { Text(stringResource(id = R.string.password)) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.Close
            } else {
                Icons.Filled.Search
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = image,
                    contentDescription = stringResource(id = R.string.show_password),
                    tint = UltraPurple
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Lock, contentDescription = stringResource(id = R.string.password))
        }
    )
}