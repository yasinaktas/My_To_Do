package com.yapss.my_to_do.presentation.tags

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yapss.my_to_do.R
import com.yapss.my_to_do.presentation._components.ComponentTextFieldOutlined
import com.yapss.my_to_do.presentation._components.ComponentTransparentButton

@Composable
fun AddTag(dismiss:()->Unit, addTag:(tag:String)->Unit){
    val context = LocalContext.current
    val tagText = remember { mutableStateOf("") }
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
                text = stringResource(R.string.new_tag),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1.0f))
            ComponentTransparentButton(stringResource(R.string.add)) {
                if(tagText.value.isNotEmpty()){
                    addTag(tagText.value)
                    dismiss()
                }else{
                    Toast.makeText(context, context.getString(R.string.please_fill_all_fields), Toast.LENGTH_SHORT).show()
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ComponentTextFieldOutlined(text = tagText.value, placeHolder = stringResource(R.string.tag)) { newText ->
            tagText.value = newText
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun PreviewAddTag(){
    AddTag(dismiss = {}){}
}

