package com.yapss.my_to_do.data.model.dto

data class DtoToDo(
    val title:String,
    val description:String,
    val dueDateString:String?,
    val dueDate:Long?,
    val priority:Int,
    val status:String,
    val date:Long
)