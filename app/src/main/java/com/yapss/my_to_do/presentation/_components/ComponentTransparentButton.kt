package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComponentTransparentButton(text:String,click:()->Unit){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { click() }
            .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f))
            .padding(8.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.surface,
                fontSize = 14.sp
            )
        )
    }
}