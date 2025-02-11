package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComponentCard(
    content: @Composable () -> Unit,
    tonalElevation:Double = 0.5,
    shadowElevation:Double = 2.0,
    radius:Int = 8
){
    Surface(
        tonalElevation = tonalElevation.dp,
        shadowElevation = shadowElevation.dp,
        shape = RoundedCornerShape(radius.dp),
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.clickable {  }
    ) {
        content()
    }
}

@Composable
fun ComponentCardStrong(content: @Composable () -> Unit){
    ComponentCard(content = content, shadowElevation = 4.0)
}

