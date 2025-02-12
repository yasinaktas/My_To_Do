package com.yapss.my_to_do.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TagWithToDos(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = ToDo::class,
        associateBy = Junction(
            value = Tag::class,
            parentColumn = "id",
            entityColumn = "todo_id"
        )
    )
    val todos: List<ToDo>
)
