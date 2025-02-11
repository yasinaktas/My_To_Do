package com.yapss.my_to_do.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yapss.my_to_do.data.model.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todo:ToDo)

    @Update
    suspend fun updateTodo(todo:ToDo)

    @Delete
    suspend fun deleteTodo(todo:ToDo)

    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun getAllTodos():LiveData<List<ToDo>>

    @Query("""
        SELECT * 
        FROM todo
        WHERE  
                description LIKE '%' || :description || '%' 
                AND (:status = '' OR status = :status)
        ORDER BY date DESC
           """)
    suspend fun getAllTodosFiltered(description:String,status:String):List<ToDo>

}