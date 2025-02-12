package com.yapss.my_to_do.presentation.tags.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapss.my_to_do.data.model.TagWithToDos
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.data.model.dto.DtoTagWithToDos
import com.yapss.my_to_do.data.repository.TagRepository
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.ConvertToDto
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TagViewModel(
    val tagRepository: TagRepository,
    val formatDateUseCase: FormatDateUseCase,
    val convertToDto: ConvertToDto,
    val todoRepository: ToDoRepository
):ViewModel() {

    private val _tagsWithToDos:MutableStateFlow<List<TagWithToDos>> = MutableStateFlow(emptyList())
    val tagsWithToDos:StateFlow<List<TagWithToDos>> = _tagsWithToDos

    init {
        viewModelScope.launch {
            tagRepository.getAllTagsWithToDosFlow().collect{ list ->
                _tagsWithToDos.value = list
            }

        }
    }

    fun dtoToTagWithToDos(dtoTagWithToDos: DtoTagWithToDos):TagWithToDos{
        return TagWithToDos(
            tagName = dtoTagWithToDos.tagName,
            todos = dtoTagWithToDos.todos.map { dtoToDo -> convertToDto.dtoToToDo(dtoToDo) }
        )
    }

    fun tagWithToDosToDto(tagWithToDos: TagWithToDos):DtoTagWithToDos{
        return DtoTagWithToDos(
            tagName = tagWithToDos.tagName,
            todos = tagWithToDos.todos.map { todo -> convertToDto.toDoToDto(todo) }
        )
    }

    fun updateToDoStatus(todo:ToDo,newStatus:String){
        viewModelScope.launch {
            todoRepository.update(todo.copy(status = newStatus))
            tagRepository.getAllTagsWithToDosFlow().collect{ list ->
                _tagsWithToDos.value = list
            }
        }
    }

}