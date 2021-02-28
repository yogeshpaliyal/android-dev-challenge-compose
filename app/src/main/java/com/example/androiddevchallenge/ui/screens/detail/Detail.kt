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
package com.example.androiddevchallenge.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.DogModel

/*
@Author : Yogesh Choudhary Paliyal
Created On  : 28-02-2021 12:51
*/
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
