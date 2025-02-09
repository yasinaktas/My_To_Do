package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yapss.my_to_do.R

@Preview
@Composable
fun PreviewComponentPremiumButton(){
    ComponentPremiumButton(click = {})
}

@Composable
fun ComponentPremiumButton(click:()->Unit){
    OutlinedButton(
        onClick = click,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(start = 16.dp,end = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_crown),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Try Premium",
            color = MaterialTheme.colorScheme.onSurface
        )
        IconButton(
            modifier = Modifier
                .padding(start = 8.dp, end = 4.dp)
                .size(32.dp),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}