package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RoundedOutlinedRectangle(width:Int,height:Int) {
    Box(
        modifier = Modifier
            .size(width = width.dp, height = height.dp).padding(2.dp)
            .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(2.dp))
    )
}

@Composable
fun FilledOutlinedRectangle(width:Int,height:Int) {
    Box(
        modifier = Modifier
            .size(width = width.dp, height = height.dp).padding(2.dp)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.25f))
            .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(2.dp))
    )
}

