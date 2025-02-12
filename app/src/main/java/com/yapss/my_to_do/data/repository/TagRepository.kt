package com.yapss.my_to_do.data.repository

import com.yapss.my_to_do.data.dao.TagDao
import com.yapss.my_to_do.data.model.Tag
import kotlinx.coroutines.flow.Flow

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

}