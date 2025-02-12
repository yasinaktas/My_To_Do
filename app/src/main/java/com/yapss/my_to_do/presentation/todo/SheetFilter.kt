package com.yapss.my_to_do.presentation.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R
import com.yapss.my_to_do.data.model.dto.DtoFilter
import com.yapss.my_to_do.data.model.sealed.Status
import com.yapss.my_to_do.presentation._components.ComponentTransparentButton

@Composable
fun FilterSheet(dismiss:()->Unit,filter:DtoFilter,applyFilter:(filter:DtoFilter)->Unit){
    val statusList = listOf(Status.All,Status.Pending,Status.Started,Status.Finished)
    val selectedStatus = remember { mutableStateOf(filter.status) }
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
                text = stringResource(R.string.filter),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1.0f))
            ComponentTransparentButton(stringResource(R.string.apply)) {
                applyFilter(DtoFilter(status = selectedStatus.value))
                dismiss()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
        ) {
            for(status in statusList){
                FilterChip(
                    selected = selectedStatus.value == status,
                    onClick = {
                        selectedStatus.value = status
                    },
                    label = {
                        Text(status.text)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun PreviewFilterSheet(){
    FilterSheet(dismiss = {}, filter = DtoFilter(status = Status.Pending), applyFilter = {})
}