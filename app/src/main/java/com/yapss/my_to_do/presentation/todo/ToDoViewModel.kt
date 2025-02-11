package com.yapss.my_to_do.presentation.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.data.model.dto.DtoToDo
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoViewModel(val toDoRepository: ToDoRepository, val formatDateUseCase: FormatDateUseCase):ViewModel() {
    val todos:LiveData<List<ToDo>> = toDoRepository.getAllTodos()

    fun insertToDo(todo:ToDo){
        viewModelScope.launch {
            toDoRepository.insert(todo)
        }
    }

    private val _description = MutableStateFlow("")
    private val _status = MutableStateFlow("")

    val filteredTodos: StateFlow<List<ToDo>> = combine(_description, _status) { description, status ->
        toDoRepository.getAllTodosFiltered(description, status)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

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