package com.appsnipp.mvvmmodel.api

import com.appsnipp.mvvmmodel.model.CategoryListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface ApiHolder {
    @GET("v1/category/all/")
    fun GetCategoryDataList(): Call<CategoryListModel>

}