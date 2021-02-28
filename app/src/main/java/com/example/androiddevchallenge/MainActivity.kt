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
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.DogModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.white

class MainActivity : AppCompatActivity() {
    private val mViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            MyTheme(darkTheme = isDarkMode) {
                MyApp(mViewModel) {
                    isDarkMode = it
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(mViewModel: MainActivityViewModel, onThemeChange: (Boolean) -> Unit) {
    val navController = rememberNavController()
    val list: List<DogModel> by mViewModel.list.observeAsState(listOf())

    NavHost(navController, startDestination = "list") {
        composable("list") { Listing(navController, list, onThemeChange) }
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
fun Listing(navController: NavController, list: List<DogModel>, onThemeChange: (isDarkMode: Boolean) -> Unit, isDarkMode: Boolean = !MaterialTheme.colors.isLight) {
    Column {
        TopAppBar(
            title = {
                Text(text = "Adopt a Dog", color = MaterialTheme.colors.onPrimary)
            },

            actions = {
                IconButton(
                    onClick = {
                        onThemeChange(!isDarkMode)
                    }
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_dark_mode_24), contentDescription = null, tint = white)
                }
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
                Text(text = dogModel.name, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onPrimary)
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = null
                    )
                }
            }
        )
        Surface(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(ScrollState(0))
        ) {
            Column(Modifier.fillMaxWidth()) {

                ConstraintLayout {
                    val (image, button) = createRefs()

                    Image(
                        painter = painterResource(id = dogModel.image),
                        contentDescription = null,
                        modifier = Modifier
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Button(
                        onClick = {
                        },
                        modifier = Modifier.constrainAs(button) {
                            top.linkTo(image.bottom)
                            bottom.linkTo(image.bottom)
                            end.linkTo(image.end, margin = 16.dp)
                        },
                        shape = RoundedCornerShape(50)
                    ) {
                        Row() {
                            Icon(painter = painterResource(id = R.drawable.ic_round_favorite_border_24), contentDescription = null)
                            Text(text = "Adopt", modifier = Modifier.padding(start = 8.dp), color = MaterialTheme.colors.onPrimary)
                        }
                    }
                }
                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Text(text = "Name", style = MaterialTheme.typography.subtitle1)
                    Text(text = dogModel.name, style = MaterialTheme.typography.body1)

                    Text(text = "Breed", style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(top = 8.dp))
                    Text(text = dogModel.breed, style = MaterialTheme.typography.body1)

                    Text(text = "Age", style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(top = 8.dp))
                    Text(text = dogModel.age, style = MaterialTheme.typography.body1)

                    Text(text = "Information", style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(top = 8.dp))
                    Text(text = dogModel.desc, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}

/**
 * @param list
 */
@Composable
fun DogsList(list: List<DogModel>, onItemSelect: (DogModel) -> Unit) {

    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
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
}

/*@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(MainActivityViewModel()){}
    }
}*/

/*@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(MainActivityViewModel())
    }
}*/
