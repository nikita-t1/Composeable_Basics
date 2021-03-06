package com.example.composeable

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeable.ui.theme.ComposeableTheme
import dev.chrisbanes.accompanist.coil.CoilImage

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

@Composable fun StartPoint(name: String, modifier: Modifier) {
    val lst = listOf("First", "Android", "Compose", "Application")
    val names: List<String> = List(25) { "Hello Android #$it" }

    ComposeableTheme {
        val counterState = remember{ mutableStateOf(0) }
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
            Divider(color = MaterialTheme.colors.background,thickness = 32.dp)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Counter(counterState.value) {
                    counterState.value = it
                }
            }
            Divider(color = MaterialTheme.colors.background,thickness = 32.dp)
            NameList(names)
        }
    }
}
@Composable fun ImageListItem(string: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CoilImage(
            data = "https://developer.android.com/images/brand/Android_Robot.png",
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$string", style = MaterialTheme.typography.subtitle1)
    }
}
@Composable fun Greeting(name: String){
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )
}

@Composable fun Counter(count: Int, updateCount: (Int) -> Unit){
    Text("This Button was clicked $count times")
    Button(onClick = {updateCount(count+1)}) {
        Text("Click Me")
    }
}

@Composable fun NameList(names: List<String>) {
    LazyColumn {
        items(items = names) { name ->
            ImageListItem(name)
        }
    }
}

@Preview(showBackground = true)
@Composable fun DefaultPreview() {
    ComposeableTheme {
        Surface(color = MaterialTheme.colors.background) {
            StartPoint("Android", Modifier.padding(8.dp))
        }
    }
}