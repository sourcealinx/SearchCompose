package com.alinx.searchcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alinx.searchcompose.ui.theme.SearchComposeTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SearchContentView()
                }
            }
        }
    }
}

@Composable
fun SearchContentView() {
    Scaffold(modifier = Modifier.padding(16.dp)) {
        val nameList = listOf("Alex", "Bob", "John", "Lily", "Tom", "Tony")

        var name by remember { mutableStateOf("") }
        var list by remember { mutableStateOf(nameList) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    var tempList = mutableListOf<String>();
                    nameList.forEach { value ->
                        if (value.contains(name)) {
                            tempList.add(value)
                        }
                    }
                    list = tempList
                },
                placeholder = {
                    Text("请输入搜索内容")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    name = ""
                                    list = nameList
                                }
                            )
                    )
                },
            )
            SearchList(list)
        }
    }
}

@Composable
fun SearchList(msgList: List<String>) {
    Column(modifier = Modifier.padding(top = 15.dp)) {
        Text("搜索结果：")
        msgList.forEach { message ->
            SearchItem(message)
        }
    }
}

@Composable
fun SearchItem(msg: String) {
    Column {
        Text(text = msg, modifier = Modifier.padding(10.dp), fontSize = 16.sp)
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SearchComposeTheme {
        SearchContentView()
    }
}