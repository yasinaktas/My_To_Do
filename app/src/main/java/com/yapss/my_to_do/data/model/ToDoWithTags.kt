package com.yapss.my_to_do.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class ToDoWithTags(
    @Embedded val todo: ToDo,
    @Relation(
        parentColumn = "id",
        entityColumn = "todo_id"
    )
    val tags: List<Tag>
)