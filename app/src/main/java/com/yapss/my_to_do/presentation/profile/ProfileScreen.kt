package com.yapss.my_to_do.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yapss.my_to_do.app.ThisApplication
import com.yapss.my_to_do.app.di.ProfileViewModelFactory
import com.yapss.my_to_do.presentation._components.ComponentCardStrong
import com.yapss.my_to_do.presentation.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
    val application = LocalContext.current.applicationContext as ThisApplication
    val viewModel:ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(apiClient = application.apiClient)
    )
    val connectionMessage = remember { mutableStateOf("") }
    LaunchedEffect(Unit){
        connectionMessage.value = viewModel.getConnectionMessage()
    }
    Box (modifier = modifier){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ComponentCardStrong {
                Row (
                    modifier = Modifier.fillMaxWidth().height(55.dp).background(MaterialTheme.colorScheme.surface),
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

                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = connectionMessage.value
                )
            }
        }
    }
}