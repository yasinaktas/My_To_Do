package com.yapss.my_to_do.presentation.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.data.model.dto.DtoToDo
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoViewModel(val toDoRepository: ToDoRepository, val formatDateUseCase: FormatDateUseCase):ViewModel() {

    fun insertToDo(todo:ToDo){
        viewModelScope.launch {
            toDoRepository.insert(todo)
        }
    }

    fun updateToDo(todo:ToDo){
        viewModelScope.launch {
            toDoRepository.update(todo)
        }

    }

    private val _description = MutableStateFlow("")
    private val _status = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredTodos: StateFlow<List<ToDo>> =
        _description.combine(_status) { description, status ->
            description to status
        }
            .flatMapLatest { (description, status) ->
                toDoRepository.getAllTodosFiltered(description, status)
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun setFilter(description: String, status: String) {
        _description.value = description
        _status.value = status
    }


    fun formatDate(timestamp:Long):String{
        return formatDateUseCase.formatFullDate(timestamp)
    }

    fun formatTime(timestamp:Long):String{
        return formatDateUseCase.formatTime(timestamp)
    }

    fun formatDateTime(timestamp:Long):String{
        return formatDateUseCase.formatDateTime(timestamp)
    }

    fun compareDates(date1:Long, date2:Long):Boolean{
        return formatDate(date1) == formatDate(date2)
    }

    private fun ToDo.toDto() = DtoToDo(title = title, description = description, dueDate = if(dueDate == null)"" else formatDate(dueDate), priority = priority, status = status, date = formatDateTime(date))

    fun convertDto(todo:ToDo):DtoToDo{
        return todo.toDto()
    }

}