package com.yapss.my_to_do.data.repository

import com.yapss.my_to_do.data.dao.TagDao
import com.yapss.my_to_do.data.model.Tag
import com.yapss.my_to_do.data.model.TagWithToDos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TagRepository(private val tagDao: TagDao) {

    suspend fun insertTag(tag:Tag){
        tagDao.insertTag(tag)
    }

    suspend fun updateTag(tag:Tag){
        tagDao.updateTag(tag)
    }

    suspend fun deleteTag(tag:Tag){
        tagDao.deleteTag(tag)
    }

    fun getTagsForTodo(todoId:Long):Flow<List<Tag>> = tagDao.getTagsForTodo(todoId)

    fun getAllTagsWithToDosFlow(): Flow<List<TagWithToDos>> = flow {
        tagDao.getDistinctTagNamesFlow().collect { tagNames ->
            val result = tagNames.map { tagName ->
                val todos = tagDao.getToDosByTagNameFlow(tagName)
                TagWithToDos(tagName, todos)
            }
            emit(result)
        }
    }.flowOn(Dispatchers.IO)


}