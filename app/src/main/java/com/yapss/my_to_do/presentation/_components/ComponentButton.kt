package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewComponentButton(){
    ComponentButton(label = "Button",click = {

    })
}

@Composable
fun ComponentButton(
    paddingTop:Int = 12,
    paddingBottom:Int = 12,
    paddingStart:Int = 12,
    paddingEnd:Int = 12,label:String,click:(() -> Unit)){
    Button(
        onClick = click,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(top = paddingTop.dp,bottom = paddingBottom.dp, start = paddingStart.dp, end = paddingEnd.dp),
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun ComponentButtonMaxWidth(
    paddingTop:Int = 12,
    paddingBottom:Int = 12,
    paddingStart:Int = 12,
    paddingEnd:Int = 12,label:String,click:(() -> Unit)){
    Button(
        onClick = click,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier.fillMaxWidth().padding(top = paddingTop.dp,bottom = paddingBottom.dp, start = paddingStart.dp, end = paddingEnd.dp),
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}