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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.DogModel
import com.example.androiddevchallenge.ui.screens.detail.Detail
import com.example.androiddevchallenge.ui.screens.list.Listing
import com.example.androiddevchallenge.ui.theme.MyTheme

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
