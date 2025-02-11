package com.yapss.my_to_do.presentation.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yapss.my_to_do.R
import com.yapss.my_to_do.app.ThisApplication
import com.yapss.my_to_do.app.di.ToDoViewModelFactory
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.presentation._components.ComponentBottomSheet
import com.yapss.my_to_do.presentation._components.ComponentCardStrong
import com.yapss.my_to_do.presentation._components.ComponentCircleButton
import com.yapss.my_to_do.presentation._components.ComponentImageButton
import com.yapss.my_to_do.presentation._components.ComponentTextField

@Composable
fun ToDoScreen(modifier: Modifier = Modifier){
    val application = LocalContext.current.applicationContext as ThisApplication
    val viewModel:ToDoViewModel = viewModel(
        factory = ToDoViewModelFactory(todoRepository = application.todoRepository, formatDateUseCase = application.formatDateUseCase)
    )
    //val todos:List<ToDo> = viewModel.todos.observeAsState(initial = emptyList()).value
    val todos:List<ToDo> = viewModel.filteredTodos.collectAsState().value
    val showSearchBar = remember { mutableStateOf(false) }
    val searchText = remember { mutableStateOf("") }
    val status = remember { mutableStateOf("pending") }
    val showAddSheet = remember { mutableStateOf(false) }
    if(showAddSheet.value){
        ComponentBottomSheet (
            content = {
                AddToDo(
                    dismiss = {
                        showAddSheet.value = false
                    },
                    add = { todo ->
                        viewModel.insertToDo(todo)
                    }
                )
            }
        ) {
            showAddSheet.value = false
        }
    }
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
                            text = "My To-Do",
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
                            viewModel.setFilter(newText, status.value)
                        }
                    }

                    ComponentImageButton(
                        icon = if(showSearchBar.value) R.drawable.baseline_close_24 else R.drawable.outline_search_24
                    ) {
                        if(showSearchBar.value){
                            searchText.value = ""
                            viewModel.setFilter(searchText.value, status.value)
                        }
                        showSearchBar.value = !showSearchBar.value
                    }

                    ComponentImageButton(
                        icon = R.drawable.baseline_filter_list_24
                    ) {

                    }

                    Spacer(modifier = Modifier.width(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                for(i in todos.indices){
                    if(i == 0 || !viewModel.compareDates(todos[i-1].date, todos[i].date)){
                        Text(
                            modifier = Modifier.padding(top = 20.dp, start = 1.dp),
                            text = viewModel.formatDate(todos[i].date),
                            fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    ToDoListItem(
                        todo = viewModel.convertDto(todos[i]),
                        onStatusChange = {
                            viewModel.updateToDo(todos[i].copy(status = it))
                            //viewModel.setFilter(searchText.value, status.value)
                        }
                    )
                }
            }


        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ){
            ComponentCircleButton(icon = R.drawable.baseline_add_24) {
                showAddSheet.value = true
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewToDo(){
    ToDoScreen()
}
