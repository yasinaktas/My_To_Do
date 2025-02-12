package com.yapss.my_to_do.data.model.dto

data class DtoTagWithToDos(
    val tagName:String,
    val todos: List<DtoToDo>
)