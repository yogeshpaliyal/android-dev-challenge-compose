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
package com.example.androiddevchallenge.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/*
CREATED BY : yogesh ON 06/08/20 12:06 PM
*/

class ViewModelFactory() :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /* if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
             return SingleNetworkCallViewModel(apiHelper, dbHelper) as T
         }*/

        return super.create(modelClass)
    }
}

fun <T : ViewModel> ViewModelStoreOwner.initViewModel(viewModel: Class<T>): T = ViewModelProvider(
    this,
    ViewModelFactory()
).get(viewModel)
