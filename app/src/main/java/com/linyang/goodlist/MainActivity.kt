package com.linyang.goodlist

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linyang.goodlist.data.ShoppingItem
import com.linyang.goodlist.ui.theme.GoodListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoodListTheme {
                MainScreen()
            }
        }
    }
}

//@Composable
//private fun ShowList(shoppingItems: List<ShoppingItem>) {
//
//}

@Composable
private fun MainScreen() {
    var inputting by remember { mutableStateOf(false) }
    var editingItem by remember { mutableStateOf("") }
    val animatedFabScale by animateFloatAsState(
        if(inputting) 0f else 1f)
    val animatedInputScale by animateFloatAsState(
        if(inputting) 1f else 0f)
    var shoppingLists = remember { mutableStateListOf<ShoppingItem>()}
    Scaffold(floatingActionButton = {
        if (!inputting) {
            FloatingActionButton(onClick = {
                inputting = true
            }, Modifier.scale(animatedFabScale)) {
                Icon(Icons.Filled.Add, "add")
            }
        }
    }, topBar = {
        TopAppBar {
            Text("shopping list")
        }
    }) {
        Box(Modifier.fillMaxSize()) {
            androidx.compose.material.Surface(Modifier.padding(16.dp), elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)) {
                LazyColumn (Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(shoppingLists) { shoppingItem ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = shoppingItem.completed, onCheckedChange = {
                                    changed -> shoppingItem.completed = changed
                            })
                            IconButton(onClick = {
                                shoppingLists.remove(shoppingItem);
                            }) {
                                Icon(Icons.Filled.Delete, "delete")
                            }
                            Text(shoppingItem.name)

                        }
                    }
                }
            }

            if (inputting) {
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .scale(animatedInputScale)) {
                    TextField(value = editingItem, onValueChange = {editingItem = it}, Modifier.weight(1f))
                    IconButton(onClick = {
                        inputting = false
                        var newItem = ShoppingItem()
                        newItem.name = editingItem
                        newItem.completed = false
                        shoppingLists.add(newItem)
                        editingItem = ""
                    }) {
                        Icon(Icons.Filled.Send, "ok")
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}