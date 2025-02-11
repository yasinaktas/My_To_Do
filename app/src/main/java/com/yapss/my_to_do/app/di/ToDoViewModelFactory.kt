package com.yapss.my_to_do.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase
import com.yapss.my_to_do.presentation.todo.ToDoViewModel

class ToDoViewModelFactory(
    private val todoRepository: ToDoRepository,
    private val formatDateUseCase: FormatDateUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ToDoViewModel::class.java)){
            return ToDoViewModel(toDoRepository = todoRepository, formatDateUseCase = formatDateUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}