package com.junior.delivery.details.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.junior.delivery.ui.theme.UltraPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTextField(
    value: String,
    placeholder: String,
    label: String,
    onTextChanged: (String) -> Unit,
    imageVector: ImageVector
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
        onValueChange = onTextChanged ,
        colors = textFieldColors,
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, UltraPurple),
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = label)
        }
    )
}