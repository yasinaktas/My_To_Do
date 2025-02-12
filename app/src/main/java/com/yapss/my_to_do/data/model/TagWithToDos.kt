package com.yapss.my_to_do.data.model

data class TagWithToDos(
    val tagName:String,
    val todos: List<ToDo>
)
