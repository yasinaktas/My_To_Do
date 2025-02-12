package com.yapss.my_to_do.data.model.dto


data class DtoToDoWithTags(
    val todo:DtoToDo,
    val tags:List<DtoTag>
)