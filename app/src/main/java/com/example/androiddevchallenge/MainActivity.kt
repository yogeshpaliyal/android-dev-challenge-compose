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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.DogModel
import com.example.androiddevchallenge.ui.theme.MyTheme

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
    val navController = rememberNavController()
    val list: List<DogModel> by mViewModel.list.observeAsState(listOf())

    NavHost(navController, startDestination = "list") {
        composable("list") { Listing(navController, list) }
        composable(
            "detail/{dog}",
            arguments = listOf(
                navArgument(
                    "dog",
                    builder = {
                        type = NavType.IntType
                    }
                )
            )
        ) {
            val id = it.arguments?.getInt("dog")
            val dogModel = mViewModel.getDog(id)
            Detail(navController, dogModel)
        }
    }
}

@Composable
fun Listing(navController: NavController, list: List<DogModel>) {
    Column {
        TopAppBar(
            title = {
                Text(text = "Adopt a Dog")
            }
        )
        DogsList(list = list) {

            navController.navigate("detail/${it.id}") {
            }
        }
    }
}

@Composable
fun Detail(navController: NavController, dogModel: DogModel?) {
    dogModel ?: return
    Column {
        TopAppBar(
            title = {
                Text(text = "Umesh")
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                }
            }
        )
        Surface(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = dogModel.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

/**
 * @param list
 */
@Composable
fun DogsList(list: List<DogModel>, onItemSelect: (DogModel) -> Unit) {

    LazyColumn(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        items(
            items = list,
            itemContent = { item ->
                Card(
                    Modifier
                        .padding(8.dp)
                        .clickable(
                            true,
                            onClick = {
                                onItemSelect(item)
                            }
                        )
                        .focusable(true)
                        .fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Image(
                            modifier = Modifier
                                .padding(top = 8.dp, start = 8.dp)
                                .height(50.dp)
                                .aspectRatio(1f)
                                .clip(CircleShape),
                            painter = painterResource(id = item.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(text = item.name, style = MaterialTheme.typography.h6)
                            Text(text = item.breed, style = MaterialTheme.typography.subtitle1)
                        }
                    }
                }
            }
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(MainActivityViewModel())
    }
}

/*@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(MainActivityViewModel())
    }
}*/
