package com.yapss.my_to_do.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yapss.my_to_do.data.dao.TagDao
import com.yapss.my_to_do.data.dao.TodoDao
import com.yapss.my_to_do.data.model.Tag
import com.yapss.my_to_do.data.model.ToDo


@Database(entities = [ToDo::class, Tag::class], version = 2, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase(){
    abstract fun todoDao(): TodoDao
    abstract fun tagDao(): TagDao
}