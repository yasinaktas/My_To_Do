package com.yapss.my_to_do.presentation.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R
import com.yapss.my_to_do.presentation._components.ComponentCardStrong
import com.yapss.my_to_do.presentation._components.ComponentTextField

@Composable
fun ToDo(){
    val showSearchBar = remember { mutableStateOf(false) }
    val searchText = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ComponentCardStrong {
            Row (
                modifier = Modifier.fillMaxWidth().height(55.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                if(!showSearchBar.value){
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "My To-Do",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                }else{
                    ComponentTextField(
                        modifier = Modifier.weight(1.0f),
                        text = searchText.value,
                        placeHolder = stringResource(R.string.searchText)
                    ) { newText ->
                        if(newText.length <= 20){
                            searchText.value = newText
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1.0f))

                IconButton(
                    onClick = {
                        showSearchBar.value = !showSearchBar.value
                    }
                ) {
                    Icon(
                        painter = painterResource(
                             if(showSearchBar.value) R.drawable.baseline_close_24
                             else R.drawable.outline_search_24
                        ),
                        contentDescription = "Search"
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewToDo(){
    ToDo()
}
