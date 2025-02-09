package com.yapss.my_to_do.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yapss.my_to_do.data.dao.TodoDao
import com.yapss.my_to_do.data.model.ToDo


@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase(){
    abstract fun todoDao(): TodoDao
}