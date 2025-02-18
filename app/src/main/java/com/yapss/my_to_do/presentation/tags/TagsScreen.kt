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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yapss.my_to_do.R
import com.yapss.my_to_do.app.ThisApplication
import com.yapss.my_to_do.app.di.TagViewModelFactory
import com.yapss.my_to_do.data.model.TagWithToDos
import com.yapss.my_to_do.presentation._components.ComponentCardStrong
import com.yapss.my_to_do.presentation._components.ComponentImageButton
import com.yapss.my_to_do.presentation._components.ComponentTextField
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
    val showSearchBar = remember { mutableStateOf(false) }
    val searchText = remember { mutableStateOf("") }
    val tagsWithTodos:List<TagWithToDos> = viewModel.tagsWithToDos.collectAsState().value
    val tagsWithTodosFiltered = remember(searchText, tagsWithTodos) {
        derivedStateOf {
            if (searchText.value.isEmpty()) tagsWithTodos
            else tagsWithTodos.filter { it.tagName.contains(searchText.value, ignoreCase = true) }
        }
    }.value
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

                    if(!showSearchBar.value){
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(R.string.my_to_do),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
                    }else{
                        ComponentTextField(
                            modifier = Modifier.weight(1.0f),
                            text = searchText.value,
                            placeHolder = stringResource(R.string.searchText)
                        ) { newText ->
                            searchText.value = newText
                        }
                    }

                    ComponentImageButton(
                        icon = if(showSearchBar.value) R.drawable.baseline_close_24 else R.drawable.outline_search_24
                    ) {
                        if(showSearchBar.value){
                            searchText.value = ""
                        }
                        showSearchBar.value = !showSearchBar.value
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(tagsWithTodosFiltered) { tagWithToDos ->
                    TagListItem(tagWithToDos = viewModel.tagWithToDosToDto(tagWithToDos)) { index, status ->
                        viewModel.updateToDoStatus(tagWithToDos.todos[index], status)
                    }
                }
            }
        }
    }
}