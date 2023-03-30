package com.appsnipp.mvvmmodel.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.appsnipp.mvvmmodel.R
import com.appsnipp.mvvmmodel.api.ApiHolder
import com.appsnipp.mvvmmodel.api.Constant
import com.appsnipp.mvvmmodel.api.RetrofitHelper
import com.appsnipp.mvvmmodel.model.CategoryListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRepository {

    companion object {
        val categoryData=MutableLiveData<CategoryListModel>()
        fun getCategoryApiCall(context: Context): MutableLiveData<CategoryListModel> {
            val call = RetrofitHelper.getApiClient().create(ApiHolder::class.java).GetCategoryDataList()
            call.enqueue(object : Callback<CategoryListModel> {
                override fun onResponse(
                    call: Call<CategoryListModel>,
                    response: Response<CategoryListModel>) {
                    val data = response.body()
                    val msg = data!!.status

                    categoryData.value = CategoryListModel(data.categories,msg)

                }

                override fun onFailure(call: Call<CategoryListModel>, t: Throwable) {

                    Log.v("DEBUG : ", t.message.toString())
                }

            })
            return categoryData
        }
    }
}