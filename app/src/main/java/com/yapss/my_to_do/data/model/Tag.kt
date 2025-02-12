package com.yapss.my_to_do.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "todo_id")
    val todoId:Long
)