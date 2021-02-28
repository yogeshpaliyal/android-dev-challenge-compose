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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
@Author : Yogesh Choudhary Paliyal
Created On  : 25-02-2021 20:24
*/
class MainActivityViewModel : ViewModel() {
    private val _list = MutableLiveData<List<DogModel>>()
    val list: LiveData<List<DogModel>> = _list

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList = ArrayList<DogModel>()

            tempList.add(DogModel(1, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(2, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(3, "Max", "French Bulldog", R.drawable.french_bulldog_cute_puppies, "4 Months", "The French Bulldog is a breed of domestic dog, bred to be companion dogs. The breed is the result of a cross between Toy Bulldogs imported from England, and local ratters in Paris, France, in the 1800s. They are stocky, compact dogs with a friendly, mild-mannered temperament"))
            tempList.add(DogModel(4, "Molly", "Poodle", R.drawable.poodle_cute_puppies, "6 Months", "The Poodle is a dog breed that comes in three varieties: Standard Poodle, Miniature Poodle, and Toy Poodle. The breed’s origin is disputed: whether it descends from Germany as a type of water dog, or from the French Barbet."))
            tempList.add(DogModel(5, "Lola & Jack", "Pug", R.drawable.pug_cute_puppies, "2 & 3 Months", "The pug is a breed of dog with physically distinctive features of a wrinkly, short-muzzled face, and curled tail. The breed has a fine, glossy coat that comes in a variety of colours, most often light brown or black, and a compact, square body with well-developed muscles."))
            tempList.add(DogModel(6, "Rocky", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(7, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(8, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(9, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(10, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(11, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(12, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(13, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(14, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(15, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(16, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(17, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(18, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))
            tempList.add(DogModel(19, "Bella", "German Shepherd", R.drawable.pubppy_german_shephard, "7 Months", "The German Shepherd is a breed of medium to large-sized working dog that originated in Germany. According to the FCI, the breed's English language name is German Shepherd Dog."))
            tempList.add(DogModel(20, "Daisy", "Golden Retriever", R.drawable.img_gold_retriever, "3 Months", "The Golden Retriever is a medium-large gun dog that was bred to retrieve shot waterfowl, such as ducks and upland game birds, during hunting and shooting parties. The name \"retriever\" refers to the breed's ability to retrieve shot game undamaged due to their soft mouth."))

            _list.postValue(tempList)
        }
    }

    fun getDog(id: Int?) = list.value?.find { it.id == id }
}
