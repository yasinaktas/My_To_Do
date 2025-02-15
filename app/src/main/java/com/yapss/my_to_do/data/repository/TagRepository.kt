package com.yapss.my_to_do.data.repository

import com.yapss.my_to_do.data.dao.TagDao
import com.yapss.my_to_do.data.model.Tag
import com.yapss.my_to_do.data.model.TagWithToDos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

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

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAllTagsWithToDosFlow(): Flow<List<TagWithToDos>> =
        tagDao.getDistinctTagNamesFlow()
            .flatMapLatest { tagNames ->
                if (tagNames.isEmpty()) {
                    flowOf(emptyList())
                } else {
                    combine(
                        tagNames.map { tagName ->
                            tagDao.getToDosByTagNameFlow(tagName)
                                .map { todos ->
                                    TagWithToDos(tagName, todos.sortedByDescending { it.date })
                                }
                        }
                    ) { tagWithToDosList ->
                        tagWithToDosList.toList()
                    }
                }
            }
            .flowOn(Dispatchers.IO)






}