package com.yapss.my_to_do.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yapss.my_to_do.R
import com.yapss.my_to_do.app.ThisApplication
import com.yapss.my_to_do.app.di.CalendarViewModelFactory
import com.yapss.my_to_do.presentation._components.ComponentCard
import com.yapss.my_to_do.presentation._components.ComponentCardStrong
import com.yapss.my_to_do.presentation._components.ComponentImageButton
import com.yapss.my_to_do.presentation.calendar.viewmodel.CalendarViewModel
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(modifier: Modifier = Modifier){
    val application = LocalContext.current.applicationContext as ThisApplication
    val viewModel: CalendarViewModel = viewModel(
        factory = CalendarViewModelFactory(
            todoRepository = application.todoRepository,
            tagRepository = application.tagRepository,
            formatDateUseCase = application.formatDateUseCase)
    )
    val openDatePicker = remember { mutableStateOf(false) }
    val selectedDate = rememberSaveable{ mutableLongStateOf(Date().time) }
    val isExpanded = remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance().apply {
        timeInMillis = selectedDate.longValue
    }

    if (openDatePicker.value) {
        DatePickerDialog(
            onDismissRequest = { openDatePicker.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDatePicker.value = false
                    }
                ) {
                    Text(stringResource(R.string.ok))
                }
            }
        ) {
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDate.longValue)
            DatePicker(state = datePickerState)

            val selectedMillis = datePickerState.selectedDateMillis
            selectedDate.longValue = selectedMillis ?: Date().time
        }
    }

    Box (modifier = modifier){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ComponentCardStrong {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "My To-Do",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.weight(1.0f))

                    ComponentImageButton(
                        icon = R.drawable.outline_calendar_today_24
                    ) {
                        openDatePicker.value = true
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            ComponentCard(modifier = Modifier,content = {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(
                                R.string.due,
                                viewModel.formatFullDate(selectedDate.longValue)
                            ),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.weight(1.0f))

                        ComponentImageButton(
                            icon = if(isExpanded.value) R.drawable.baseline_keyboard_arrow_right_24 else R.drawable.baseline_keyboard_arrow_down_24
                        ) {
                            isExpanded.value = !isExpanded.value
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    if(isExpanded.value){
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)


                        val firstDayOfMonth = Calendar.getInstance().apply {
                            set(year, month, 1)
                        }

                        val daysInMonth = firstDayOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH)

                        val timestampsList = (1..daysInMonth).map { day ->
                            Calendar.getInstance().apply {
                                set(year, month, day, 0, 0, 0)
                                set(Calendar.MILLISECOND, 0)
                            }.timeInMillis
                        }

                        val firstDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK)

                        val weekDays = stringArrayResource(id = R.array.weekdays).toList()

                        val emptyCells = (firstDayOfWeek + 5) % 7
                        val daysList = List(emptyCells) { "" } + (1..daysInMonth).map { it.toString() }

                        val calendarItems = weekDays + daysList

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(7),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(calendarItems.size) { index ->
                                    if(index >= emptyCells+weekDays.size && viewModel.compareDates(selectedDate.longValue,timestampsList[index-emptyCells-weekDays.size])){
                                        Box(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .size(40.dp)
                                                .background(if (calendarItems[index].isNotEmpty() && !weekDays.contains(calendarItems[index])) MaterialTheme.colorScheme.primary.copy(alpha = 0.25f) else Color.Transparent, shape = RoundedCornerShape(8.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = calendarItems[index],
                                                fontSize = 16.sp,
                                                fontWeight = if (weekDays.contains(calendarItems[index])) FontWeight.Bold else FontWeight.Normal,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }else{
                                        Box(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .size(40.dp)
                                                .background(if (calendarItems[index].isNotEmpty() && !weekDays.contains(calendarItems[index])) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f) else Color.Transparent, shape = RoundedCornerShape(8.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = calendarItems[index],
                                                fontSize = 16.sp,
                                                fontWeight = if (weekDays.contains(calendarItems[index])) FontWeight.Bold else FontWeight.Normal,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            })
        }
    }
}