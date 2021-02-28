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
package com.example.androiddevchallenge.ui.screens.list

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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.DogModel
import com.example.androiddevchallenge.ui.theme.white

/*
@Author : Yogesh Choudhary Paliyal
Created On  : 28-02-2021 12:51
*/

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
