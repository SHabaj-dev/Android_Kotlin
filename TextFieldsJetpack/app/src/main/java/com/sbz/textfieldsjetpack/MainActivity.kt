package com.sbz.textfieldsjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sbz.textfieldsjetpack.ui.theme.TextFieldsJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldsJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember {
            mutableStateOf("Enter Something...")
        }
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
//            readOnly = true,
            label = {
                Text(text = "Title")
            },
            singleLine = true,
            shape = MaterialTheme.shapes.small
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextFieldsJetpackTheme {
        Greeting()
    }
}