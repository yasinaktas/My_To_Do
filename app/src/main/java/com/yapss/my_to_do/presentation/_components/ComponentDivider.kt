package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewComponentDivider(){
    ComponentDivider()
}

@Composable
fun ComponentDivider(paddingStart:Int = 0, paddingEnd:Int = 0,paddingTop:Int = 0,paddingBottom:Int = 0){
    HorizontalDivider(
        modifier = Modifier.padding(start = paddingStart.dp, end = paddingEnd.dp, top = paddingTop.dp, bottom = paddingBottom.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}