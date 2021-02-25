/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.data.DogModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : AppCompatActivity() {
    private val mViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(mViewModel)
            }
        }
    }
}


// Start building your app here!
@Composable
fun MyApp(mViewModel: MainActivityViewModel) {

    val list: List<DogModel> by mViewModel.list.observeAsState(listOf())
    Column {
        TopAppBar(title = {
            Text(text = "yo")
        })
        LazyColumn(Modifier.fillMaxHeight().fillMaxWidth()) {
            items(items = list, itemContent = { item ->
                Card(
                    Modifier
                        .padding(8.dp).fillMaxWidth(),elevation = 2.dp) {
                    Column(Modifier.fillMaxWidth().padding(8.dp)) {
                        Text(text = item.name, fontSize = 16.sp)
                        Text(text = "subtitle",fontSize = 12.sp)
                    }
                }
            })
        }
    }

}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(MainActivityViewModel())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(MainActivityViewModel())
    }
}
