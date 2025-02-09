package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ComponentCircleButton(icon:Int,click: () -> Unit){
    Button(
        modifier = Modifier.size(60.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        onClick = click,
    ) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}