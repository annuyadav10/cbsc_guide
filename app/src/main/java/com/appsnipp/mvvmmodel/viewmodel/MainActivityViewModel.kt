package com.appsnipp.mvvmmodel.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsnipp.mvvmmodel.model.CategoryListModel
import com.appsnipp.mvvmmodel.repository.MainActivityRepository

class MainActivityViewModel:ViewModel() {

        var category: MutableLiveData<CategoryListModel>? = null

        open  fun getCategoryData(context: Context): LiveData<CategoryListModel>? {
            category = MainActivityRepository.getCategoryApiCall(context)
            return category
        }

}