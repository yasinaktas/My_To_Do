package com.yapss.my_to_do.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yapss.my_to_do.data.repository.TagRepository
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.ConvertToDto
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase
import com.yapss.my_to_do.presentation.tags.viewmodel.TagViewModel
import com.yapss.my_to_do.presentation.todo.viewmodel.ToDoViewModel

class TagViewModelFactory(
    private val tagRepository: TagRepository,
    private val formatDateUseCase: FormatDateUseCase,
    private val convertToDto: ConvertToDto,
    private val todoRepository: ToDoRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TagViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TagViewModel(tagRepository = tagRepository, formatDateUseCase = formatDateUseCase,convertToDto = convertToDto,todoRepository = todoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}