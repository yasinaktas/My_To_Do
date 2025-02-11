package com.yapss.my_to_do.presentation.todo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R
import com.yapss.my_to_do.data.model.ToDo
import com.yapss.my_to_do.presentation._components.ComponentImageButton
import com.yapss.my_to_do.presentation._components.ComponentTextFieldOutlined
import com.yapss.my_to_do.presentation._components.ComponentTransparentButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDo(dismiss:()->Unit,add:(todo:ToDo)->Unit){
    val context = LocalContext.current
    val titleText = remember { mutableStateOf("") }
    val descriptionText = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf("") }
    val openDatePicker = remember { mutableStateOf(false) }
    val priorityNumber = remember { mutableIntStateOf(1) }

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
            val datePickerState = rememberDatePickerState()
            DatePicker(state = datePickerState)

            val selectedMillis = datePickerState.selectedDateMillis
            selectedDate.value = if (selectedMillis != null) {
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(selectedMillis))
            } else {
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.new_task),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1.0f))
            ComponentTransparentButton(stringResource(R.string.save)) {
                if(titleText.value.isNotEmpty() && descriptionText.value.isNotEmpty()){
                    add(ToDo(
                        title = titleText.value,
                        description = descriptionText.value,
                        date = Date().time,
                        priority = priorityNumber.intValue,
                        status = "pending",
                        dueDate = if(selectedDate.value.isNotEmpty()) Date().time else null)
                    )
                    dismiss()
                }else{
                    Toast.makeText(context, context.getString(R.string.please_fill_all_fields), Toast.LENGTH_SHORT).show()
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ComponentTextFieldOutlined(text = titleText.value, placeHolder = stringResource(R.string.title)) { newText ->
            titleText.value = newText
        }

        Spacer(modifier = Modifier.height(12.dp))

        ComponentTextFieldOutlined(text = descriptionText.value, placeHolder = stringResource(R.string.description)) { newText ->
            descriptionText.value = newText
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(R.string.due_date),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1.0f))
            if(selectedDate.value.isNotEmpty()){
                AssistChip(
                    onClick = {

                    },
                    label = {
                        Text(selectedDate.value)
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_close_24),
                            contentDescription = "Due Date",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.clickable {
                                selectedDate.value = ""
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            ComponentImageButton(
                icon = R.drawable.outline_calendar_month_24
            ) {
                openDatePicker.value = true
            }

        }

        Spacer(modifier = Modifier.height(12.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(R.string.priority),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1.0f))
            for (i in 1..5){
                FilterChip(selected = priorityNumber.intValue == i, onClick = {
                    priorityNumber.intValue = i
                }, label = { Text("$i") })
            }

        }

        Spacer(modifier = Modifier.height(12.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(R.string.tags),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1.0f))
            ComponentImageButton(
                icon = R.drawable.baseline_add_24
            ) {

            }
        }



        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun PreviewAddToDo(){
    AddToDo(dismiss = {}){}
}