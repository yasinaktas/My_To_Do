package com.yapss.my_to_do.presentation.calendar.viewmodel

import androidx.lifecycle.ViewModel
import com.yapss.my_to_do.data.repository.TagRepository
import com.yapss.my_to_do.data.repository.ToDoRepository
import com.yapss.my_to_do.domain.usecase.FormatDateUseCase

class CalendarViewModel(
    private val todoRepository: ToDoRepository,
    private val tagRepository: TagRepository,
    private val formatDateUseCase: FormatDateUseCase,
):ViewModel() {

    fun formatMonthYear(time:Long):String{
        return formatDateUseCase.formatMonthYear(time)
    }

    fun formatFullDate(time:Long):String{
        return formatDateUseCase.formatFullDate(time)
    }

    fun compareDates(date1:Long,date2:Long):Boolean{
        return formatDateUseCase.formatFullDate(date1) == formatDateUseCase.formatFullDate(date2)
    }
}