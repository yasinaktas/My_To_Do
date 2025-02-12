package com.yapss.my_to_do.presentation.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapss.my_to_do.data.model.Tag
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.data.model.ToDoWithTags
import com.yapss.my_to_do.data.model.dto.DtoFilter
import com.yapss.my_to_do.data.model.dto.DtoTag
import com.yapss.my_to_do.data.model.dto.DtoToDo
import com.yapss.my_to_do.data.model.dto.DtoToDoWithTags
import com.yapss.my_to_do.data.model.sealed.Status
import com.yapss.my_to_do.data.repository.TagRepository
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

class ToDoViewModel(val toDoRepository: ToDoRepository, val tagRepository: TagRepository, val formatDateUseCase: FormatDateUseCase):ViewModel() {

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
    val filteredTodos: StateFlow<List<ToDoWithTags>> =
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

    fun parseDate(date:String):Long{
        return formatDateUseCase.parseDate(date)
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

    fun convertDto(todoWithTags:ToDoWithTags):DtoToDoWithTags{
        return DtoToDoWithTags(
            todo = DtoToDo(
                title = todoWithTags.todo.title,
                description = todoWithTags.todo.description,
                dueDate = todoWithTags.todo.dueDate,
                dueDateString = if(todoWithTags.todo.dueDate == null) null else formatDateUseCase.formatFullDate(todoWithTags.todo.dueDate),
                priority = todoWithTags.todo.priority,
                status = todoWithTags.todo.status,
                date = todoWithTags.todo.date
            ),
            tags = todoWithTags.tags.map { tag -> DtoTag(name = tag.name) }
        )
    }

    fun insertTag(tag: Tag){
        viewModelScope.launch {
            tagRepository.insertTag(tag)
        }
    }

    fun insertToDoWithTags(todoWithTags: DtoToDoWithTags){
        viewModelScope.launch {
            val todoId:Long = toDoRepository.insert(todo = ToDo(
                title = todoWithTags.todo.title,
                description = todoWithTags.todo.description,
                dueDate = todoWithTags.todo.dueDate,
                priority = todoWithTags.todo.priority,
                status = todoWithTags.todo.status,
                date = todoWithTags.todo.date
            ))
            for(tag in todoWithTags.tags){
                tagRepository.insertTag(Tag(name = tag.name, todoId = todoId))
            }
        }
    }

    private var filter:DtoFilter = DtoFilter(status = Status.All)

    fun getFilter():DtoFilter{
        return filter
    }

    fun setFilterStatus(status:Status){
        this.filter.status = status
        setFilter(description = _description.value, status = if(status == Status.All) "" else status.status)
    }

}