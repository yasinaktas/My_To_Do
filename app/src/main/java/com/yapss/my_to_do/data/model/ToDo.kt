package com.yapss.my_to_do.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo")
data class ToDo (
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "description")
    val description:String,
    @ColumnInfo(name = "date")
    val date:Date,
    @ColumnInfo(name = "priority")
    val priority:Int,
    @ColumnInfo(name = "status")
    val status:String,
    @ColumnInfo(name = "due_date")
    val dueDate:Date
)