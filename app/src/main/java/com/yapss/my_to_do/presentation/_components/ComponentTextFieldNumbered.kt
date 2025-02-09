package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewComponentTextFieldNumbered(){
    val text = remember {
        mutableStateOf("")
    }
    ComponentTextFieldNumbered(text.value,"0"){ newText->
        text.value = newText
    }
}

@Composable
fun ComponentTextFieldNumbered(text:String,placeHolder:String,onTextChange:(String) -> Unit){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp,top = 4.dp, bottom = 4.dp),
        value = text,
        singleLine = true,
        onValueChange =
            onTextChange
        ,
        placeholder = {
            Text(
                text = placeHolder,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.75f)
            )
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )
}