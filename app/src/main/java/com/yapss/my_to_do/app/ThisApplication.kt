package com.yapss.my_to_do.app

import android.app.Application
import androidx.room.Room
import com.yapss.my_to_do.data.database.ToDoDatabase
import com.yapss.my_to_do.data.repository.ToDoRepository

class ThisApplication: Application() {

    private lateinit var todoDatabase:ToDoDatabase
    lateinit var todoRepository:ToDoRepository

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(this, ToDoDatabase::class.java, "todo_database").build()
        todoRepository = ToDoRepository(todoDatabase.todoDao())
    }
}