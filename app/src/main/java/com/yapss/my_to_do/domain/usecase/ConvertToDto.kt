package com.yapss.my_to_do.domain.usecase

import com.yapss.my_to_do.data.model.Tag
import com.yapss.my_to_do.data.model.TagWithToDos
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.data.model.ToDoWithTags
import com.yapss.my_to_do.data.model.dto.DtoTag
import com.yapss.my_to_do.data.model.dto.DtoTagWithToDos
import com.yapss.my_to_do.data.model.dto.DtoToDo
import com.yapss.my_to_do.data.model.dto.DtoToDoWithTags

class ConvertToDto{

    private val formatDateUseCase = FormatDateUseCase()

    fun toDoToDto(todo:ToDo):DtoToDo{
        return DtoToDo(
            title = todo.title,
            description = todo.description,
            dueDate = todo.dueDate,
            priority = todo.priority,
            status = todo.status,
            date = todo.date,
            dateString = formatDateUseCase.formatFullDate(todo.date),
            dueDateString = if(todo.dueDate == null) null else formatDateUseCase.formatFullDate(todo.date)
        )
    }

    fun dtoToToDo(dtoToDo: DtoToDo):ToDo{
        return ToDo(
            title = dtoToDo.title,
            description = dtoToDo.description,
            dueDate = dtoToDo.dueDate,
            priority = dtoToDo.priority,
            status = dtoToDo.status,
            date = dtoToDo.date
        )
    }

    fun tagToDto(tag: Tag): DtoTag {
        return DtoTag(name = tag.name)
    }

    fun dtoToTag(dtoTag: DtoTag, todoId:Long): Tag {
        return Tag(name = dtoTag.name, todoId = todoId)
    }

    fun toDoWithTagsToDto(todoWithTags:ToDoWithTags):DtoToDoWithTags{
        return DtoToDoWithTags(
            todo = toDoToDto(todoWithTags.todo),
            tags = todoWithTags.tags.map { tag -> tagToDto(tag) }
        )
    }

    fun dtoToToDoWithTags(dtoToDoWithTags: DtoToDoWithTags, todoId:Long):ToDoWithTags{
        return ToDoWithTags(
            todo = dtoToToDo(dtoToDoWithTags.todo),
            tags = dtoToDoWithTags.tags.map { tag -> dtoToTag(tag, todoId) }
        )
    }

    fun tagWithToDosToDto(tagWithToDos: TagWithToDos): DtoTagWithToDos {
        return DtoTagWithToDos(
            tagName = tagWithToDos.tagName,
            todos = tagWithToDos.todos.map { todo -> toDoToDto(todo) }
        )
    }
}