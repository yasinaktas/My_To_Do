package com.yapss.my_to_do.presentation._components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
fun PreviewComponentDateButton(){
    ComponentDateButton(date = "June 2024",icon = R.drawable.baseline_calendar_today_24,click = {})
}

@Composable
fun ComponentDateButton(date:String,icon:Int? = null,click:()->Unit){
    OutlinedButton(
        onClick = click,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        if(icon != null){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = date,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}