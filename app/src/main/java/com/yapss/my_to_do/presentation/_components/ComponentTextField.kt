package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewComponentTextField(){
    val text = remember {
        mutableStateOf("")
    }
    ComponentTextField(text = text.value, placeHolder = "Enter name..."){ newText->
        text.value = newText
    }
}

@Composable
fun ComponentTextFieldOutlined(text:String,placeHolder:String,onTextChange:(String) -> Unit){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        singleLine = true,
        onValueChange = onTextChange,
        placeholder = {
            Text(
                text = placeHolder,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.75f)
            )
        },
        shape = RoundedCornerShape(8.dp)
    )
}
@Composable
fun ComponentTextField(modifier:Modifier = Modifier, text:String,placeHolder:String,onTextChange:(String) -> Unit){
    TextField(
        modifier = modifier,
        value = text,
        singleLine = true,
        onValueChange = onTextChange,
        placeholder = {
            Text(
                text = placeHolder,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.75f)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
    )
}