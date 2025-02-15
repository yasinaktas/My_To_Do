package com.yapss.my_to_do.app

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yapss.my_to_do.api.client.ApiClient
import com.yapss.my_to_do.data.database.ToDoDatabase
import com.yapss.my_to_do.data.repository.TagRepository
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.ConvertToDto
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase

class ThisApplication: Application() {

    private lateinit var todoDatabase:ToDoDatabase
    lateinit var todoRepository:ToDoRepository
    lateinit var formatDateUseCase:FormatDateUseCase
    lateinit var tagRepository: TagRepository
    lateinit var convertToDto: ConvertToDto
    lateinit var apiClient:ApiClient

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(this, ToDoDatabase::class.java, "todo_database").addMigrations(MIGRATION_1_2).build()
        todoRepository = ToDoRepository(todoDatabase.todoDao())
        formatDateUseCase = FormatDateUseCase()
        tagRepository = TagRepository(todoDatabase.tagDao())
        convertToDto = ConvertToDto()
        apiClient = ApiClient

    }

    private val MIGRATION_1_2 = object : Migration(1,2){
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS `tags` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `todo_id` INTEGER NOT NULL)")
        }
    }
}