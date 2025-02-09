package com.yapss.my_to_do.presentation.todo

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        factory = ToDoViewModelFactory(application.todoRepository)
    )
    val todos:List<ToDo> = viewModel._todos.observeAsState(initial = emptyList()).value
    val showSearchBar = remember { mutableStateOf(false) }
    val searchText = remember { mutableStateOf("") }
    val showAddSheet = remember { mutableStateOf(false) }
    if(showAddSheet.value){
        ComponentBottomSheet (
            content = {
                AddToDo(){}
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
                    modifier = Modifier.fillMaxWidth().height(55.dp),
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

                    ComponentImageButton(
                        icon = R.drawable.baseline_filter_list_24
                    ) {

                    }

                    Spacer(modifier = Modifier.width(4.dp))
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
