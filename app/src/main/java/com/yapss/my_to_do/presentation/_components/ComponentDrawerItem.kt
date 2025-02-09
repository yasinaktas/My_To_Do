package com.yapss.my_to_do.presentation._components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R

@Preview(showBackground = true)
@Composable
fun PreviewComponentDrawerItem(){
    ComponentDrawerItem(title = "Drawer Item", icon = R.drawable.baseline_home_filled_24, selected = false) {

    }
}

@Composable
fun ComponentDrawerItem(title:String, @DrawableRes icon:Int, selected:Boolean, click:()->Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                click()
            }
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.outline,
            fontSize = 14.sp
        )
    }
}