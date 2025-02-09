package com.yapss.my_to_do.presentation.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.data.repository.ToDoRepository

class ToDoViewModel(toDoRepository: ToDoRepository):ViewModel() {
    val _todos:LiveData<List<ToDo>> = toDoRepository.getAllTodos()

}