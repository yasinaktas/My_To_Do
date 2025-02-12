package com.yapss.my_to_do.presentation.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R
import com.yapss.my_to_do.data.model.dto.DtoTag
import com.yapss.my_to_do.data.model.dto.DtoToDo
import com.yapss.my_to_do.data.model.dto.DtoToDoWithTags
import com.yapss.my_to_do.presentation._components.ComponentCard
import com.yapss.my_to_do.presentation._components.FilledOutlinedRectangle
import com.yapss.my_to_do.presentation._components.RoundedOutlinedRectangle
import java.util.Date

@Composable
fun ToDoListItem(todoWithTags: DtoToDoWithTags,onStatusChange:(status:String)->Unit){
    Column(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(vertical = 8.dp)
    ) {
        ComponentCard(content = {
            Row(
                modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp).background(MaterialTheme.colorScheme.surface).padding(vertical = 12.dp, horizontal = 4.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.width(30.dp).padding(top = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    for(i in 0..<5-todoWithTags.todo.priority){
                        RoundedOutlinedRectangle(20,20)
                    }
                    for(i in 0..<todoWithTags.todo.priority){
                        FilledOutlinedRectangle(20,20)
                    }
                }
                Column(
                    modifier = Modifier.weight(1.0f),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = todoWithTags.todo.title,
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(4.dp).padding(end = 8.dp).weight(1.0f)
                        )
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp)
                                .border(
                                    shape = RoundedCornerShape(2.dp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    width = 1.dp
                                ).clickable {
                                    onStatusChange(if(todoWithTags.todo.status == "pending") "started" else if(todoWithTags.todo.status == "started") "finished" else "pending")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if(todoWithTags.todo.status == "started"){
                                Icon(
                                    painter = painterResource(R.drawable.round_hourglass_bottom_24),
                                    contentDescription = "Started",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }else if(todoWithTags.todo.status == "finished"){
                                Icon(
                                    painter = painterResource(R.drawable.baseline_check_24),
                                    contentDescription = "Started",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    Text(
                        text = todoWithTags.todo.description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.padding(4.dp)
                    )


                    LazyRow {
                        items(todoWithTags.tags.size) { i ->
                            Spacer(modifier = Modifier.width(4.dp))
                            AssistChip(
                                onClick = {},
                                label = {
                                    Text(
                                        text = todoWithTags.tags[i].name,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                        fontSize = 12.sp
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                        }
                    }
                }

            }
        })

        if(todoWithTags.todo.dueDate != null){
            Row {
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = "Due ${todoWithTags.todo.dueDateString}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewToDoListItem(){
    ToDoListItem(
        DtoToDoWithTags(
            todo = DtoToDo(
                title = "Deneme deneme deneme deneme deneme abc",
                description = "Deneme açıklama uzun mu uzun",
                dueDateString = "12 Şubat 2025",
                dueDate = Date().time,
                priority = 1,
                status = "finished",
                date = Date().time
            ),
            tags = listOf(DtoTag("Tag1"),DtoTag("Tag2"),DtoTag("Tag3"))
        ),
        onStatusChange = {})
}