package com.yapss.my_to_do.presentation.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R
import com.yapss.my_to_do.data.model.TagWithToDos
import com.yapss.my_to_do.data.model.dto.DtoTagWithToDos
import com.yapss.my_to_do.data.model.dto.DtoToDo
import com.yapss.my_to_do.data.model.sealed.Status
import com.yapss.my_to_do.presentation._components.ComponentCard
import java.util.Date

@Composable
fun TagListItem(tagWithToDos: DtoTagWithToDos,onStatusChange:(index:Int,status:String)->Unit){
    val isExpanded = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        ComponentCard(content = {
            Row (
                modifier = Modifier.
                fillMaxWidth().
                height(55.dp).
                background(MaterialTheme.colorScheme.surface).
                clickable {
                    isExpanded.value = !isExpanded.value
                }.
                padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = tagWithToDos.tagName,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.0f)
                )
                Text(
                    text = "(${tagWithToDos.todos.size})",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(if(isExpanded.value) R.drawable.baseline_keyboard_arrow_down_24 else R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = "Arrow Expand"
                )
            }
        })

        if(isExpanded.value){
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                for(tagWithToDo in tagWithToDos.todos.sortedByDescending { it.date }){
                    TagToDoListItem(todo = tagWithToDo, index = tagWithToDos.todos.indexOf(tagWithToDo)) {index,status->
                        onStatusChange(index,status)
                    }
                }

            }
        }
    }
}