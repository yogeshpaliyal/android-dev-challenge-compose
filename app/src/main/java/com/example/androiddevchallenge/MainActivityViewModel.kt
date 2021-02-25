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
    val list : LiveData<List<DogModel>> = _list

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch(Dispatchers.IO) {
        val tempList = ArrayList<DogModel>()
            for (i in 0 until 10){
                tempList.add(DogModel("Lebra $i","Pomarian","","10 Feb 2020"))
            }
            _list.postValue(tempList)
        }
    }
}