package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R

@Preview
@Composable
fun PreviewComponentTopBar(){
    ComponentTopBar(label = "Example App",leftButton = { ComponentImageButton(icon = R.drawable.baseline_arrow_back_24, click = {}) })
}

@Composable
fun ComponentTopBar(label:String, leftButton:(@Composable ()->Unit)? = null, rightButton:(@Composable ()->Unit)? = null){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, end = 0.dp, top = 0.dp),
        shadowElevation = 0.dp,
        contentColor = MaterialTheme.colorScheme.primary,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ){
                leftButton?.let { it() }
                Text(
                    text = label,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                rightButton?.let { it() }
            }
            ComponentDivider()
        }
    }
}