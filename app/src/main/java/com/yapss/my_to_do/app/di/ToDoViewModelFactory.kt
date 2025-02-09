package com.yapss.my_to_do.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.presentation.todo.ToDoViewModel

class ToDoViewModelFactory(
    private val todoRepository: ToDoRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ToDoViewModel::class.java)){
            return ToDoViewModel(toDoRepository = todoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}