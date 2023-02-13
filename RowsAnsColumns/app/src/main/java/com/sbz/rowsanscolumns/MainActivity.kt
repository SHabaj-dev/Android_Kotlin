package com.sbz.rowsanscolumns


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbz.rowsanscolumns.ui.theme.RowsAnsColumnsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RowsAnsColumnsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
//                    BoxExample()
                    TextExample()
                }
            }
        }
    }
}

@Composable
fun ColumnScope.ColumnExample(
    weight: Float,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colors.primary
) {
    Surface(
        modifier = Modifier
            .width(200.dp)
            .weight(weight),
        color = color,
    ) {}
}

@Composable
fun BoxExample() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
                .height(100.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            Text(text = "I Love Android", fontSize = 40.sp)
        }
    }
}

@Composable
fun TextExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello World!",
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(16.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RowsAnsColumnsTheme {
        TextExample()


        /*
            BoxExample()
        */

/*        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ColumnExample(weight = 5f, color = MaterialTheme.colors.secondary)
            ColumnExample(weight = 1f, color = MaterialTheme.colors.primary)
            ColumnExample(weight = 5f, color = MaterialTheme.colors.secondary)
        }*/

    }
}