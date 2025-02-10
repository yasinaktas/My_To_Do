package com.yapss.my_to_do.presentation.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.data.repository.ToDoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(val toDoRepository: ToDoRepository):ViewModel() {
    val todos:LiveData<List<ToDo>> = toDoRepository.getAllTodos()

    fun insertToDo(todo:ToDo){
        viewModelScope.launch {
            toDoRepository.insert(todo)
        }
    }

}