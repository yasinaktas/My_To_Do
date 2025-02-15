package com.yapss.my_to_do.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yapss.my_to_do.data.repository.TagRepository
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.ConvertToDto
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase
import com.yapss.my_to_do.presentation.calendar.viewmodel.CalendarViewModel
import com.yapss.my_to_do.presentation.tags.viewmodel.TagViewModel
import com.yapss.my_to_do.presentation.todo.viewmodel.ToDoViewModel

class CalendarViewModelFactory(
    private val tagRepository: TagRepository,
    private val formatDateUseCase: FormatDateUseCase,
    private val todoRepository: ToDoRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CalendarViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CalendarViewModel(tagRepository = tagRepository, formatDateUseCase = formatDateUseCase,todoRepository = todoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}