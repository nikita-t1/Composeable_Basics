package com.example.composeable

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeable.ui.theme.ComposeableTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeableTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    StartPoint("Android", Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun StartPoint(name: String, modifier: Modifier) {
    val lst = listOf("First", "Android", "Compose", "Application")
    ComposeableTheme {
        val counterState = remember{ mutableStateOf(1) }
        Column {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )  {
                for (string in lst){
                    Text(text = string, modifier = modifier)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Counter(counterState.value) {
                    counterState.value = it
                }
            }
        }
    }
}

@Composable fun Counter(count: Int, updateCount: (Int) -> Unit){
    Text("This Button was clicked $count times")
    Button(onClick = {updateCount(count+1)}) {
        Text("Click Me")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeableTheme {
        Surface(color = MaterialTheme.colors.background) {
            StartPoint("Android", Modifier.padding(8.dp))
        }
    }
}