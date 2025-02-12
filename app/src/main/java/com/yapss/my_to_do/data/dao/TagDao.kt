package com.yapss.my_to_do.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yapss.my_to_do.data.model.Tag
import com.yapss.my_to_do.data.model.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Insert
    suspend fun insertTag(tag: Tag): Long

    @Update
    suspend fun updateTag(tag: Tag)

    @Delete
    suspend fun deleteTag(tag: Tag)

    @Query("SELECT * FROM tags WHERE todo_id = :todoId")
    fun getTagsForTodo(todoId: Long): Flow<List<Tag>>


    @Query("SELECT DISTINCT name FROM tags ORDER BY name")
    fun getDistinctTagNamesFlow(): Flow<List<String>>

    @Query("""
        SELECT todo.* FROM todo
        INNER JOIN tags ON todo.id = tags.todo_id
        WHERE tags.name = :tagName
    """)
    suspend fun getToDosByTagNameFlow(tagName: String): List<ToDo>


}