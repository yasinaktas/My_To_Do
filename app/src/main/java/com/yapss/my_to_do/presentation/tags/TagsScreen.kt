package com.yapss.my_to_do.presentation.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yapss.my_to_do.app.ThisApplication
import com.yapss.my_to_do.app.di.TagViewModelFactory
import com.yapss.my_to_do.data.model.TagWithToDos
import com.yapss.my_to_do.presentation._components.ComponentCardStrong
import com.yapss.my_to_do.presentation.tags.viewmodel.TagViewModel

@Composable
fun TagsScreen(modifier: Modifier = Modifier){
    val application = LocalContext.current.applicationContext as ThisApplication
    val viewModel:TagViewModel = viewModel(
        factory = TagViewModelFactory(
            tagRepository = application.tagRepository,
            formatDateUseCase = application.formatDateUseCase,
            convertToDto = application.convertToDto,
            todoRepository = application.todoRepository
        )
    )
    val tagsWithTodos:List<TagWithToDos> = viewModel.tagsWithToDos.collectAsState().value
    Box (modifier = modifier){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ComponentCardStrong {
                Row (
                    modifier = Modifier.fillMaxWidth().height(55.dp).background(MaterialTheme.colorScheme.surface),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "My To-Do",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.weight(1.0f))

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(tagsWithTodos.size){ i ->
                    TagListItem(tagWithToDos = viewModel.tagWithToDosToDto(tagsWithTodos[i])){index,status->
                        viewModel.updateToDoStatus(tagsWithTodos[i].todos[index], newStatus = status)
                    }
                }

            }
        }
    }
}