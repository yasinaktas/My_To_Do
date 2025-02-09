package com.yapss.my_to_do.presentation._components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.yapss.my_to_do.R

@Preview(showBackground = true)
@Composable
fun PreviewComponentImageButton(){
    ComponentImageButton(icon = R.drawable.baseline_add_24) {

    }
}

@Composable
fun ComponentImageButton(@DrawableRes icon:Int, click:()->Unit){
    IconButton(
        onClick = click
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}