package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewComponentTitle(){
    ComponentTitle(label = "Component Title")
}

@Composable
fun ComponentTitle(topPadding:Int = 20, bottomPadding:Int = 8,startPadding:Int = 16, label:String){
    Text(
        text = label,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(start = startPadding.dp,top = topPadding.dp,bottom = bottomPadding.dp)
    )
}