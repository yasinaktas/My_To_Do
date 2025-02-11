package com.yapss.my_to_do.data.repository

import com.yapss.my_to_do.data.dao.TodoDao
import com.yapss.my_to_do.data.model.ToDo

class ToDoRepository(private val todoDao: TodoDao) {

    suspend fun insert(todo:ToDo){
        todoDao.insertTodo(todo)
    }

    suspend fun update(todo:ToDo){
        todoDao.updateTodo(todo)
    }

    suspend fun delete(todo:ToDo){
        todoDao.deleteTodo(todo)
    }

    fun getAllTodos() = todoDao.getAllTodos()

    suspend fun getAllTodosFiltered(description:String,status:String) = todoDao.getAllTodosFiltered(description,status)

}