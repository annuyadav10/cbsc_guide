package com.appsnipp.mvvmmodel.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.appsnipp.mvvmmodel.R
import com.appsnipp.mvvmmodel.adapter.CategoryAdapter
import com.appsnipp.mvvmmodel.api.Constant
import com.appsnipp.mvvmmodel.databinding.ActivityMainBinding
import com.appsnipp.mvvmmodel.model.Category
import com.appsnipp.mvvmmodel.model.CategoryListModel
import com.appsnipp.mvvmmodel.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel:MainActivityViewModel
    lateinit var context: Context
    var categoryList:MutableList<Category> = arrayListOf()
    lateinit var catadapter:CategoryAdapter
    var parentLayout: View? = null
    private val SPLASH_DISPLAY_LENGTH = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this@MainActivity
        parentLayout=findViewById(android.R.id.content)
        if (isNetworkConnected()){
        viewmodel= ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewmodel.getCategoryData(this@MainActivity)!!.observe(this, Observer { catdata->
             categoryList=catdata.categories
             if(categoryList.size>0){
                 catadapter= CategoryAdapter(this@MainActivity,categoryList)
                 binding.showingcategory.layoutManager=GridLayoutManager(this,3)
                 binding.showingcategory.adapter=catadapter
                 catadapter.notifyDataSetChanged()
             }
        })
       }
        else{
            checkInternet(parentLayout)
        }
    }

    fun checkInternet(parentLayout: View?) {
        Handler().postDelayed({ finish() }, SPLASH_DISPLAY_LENGTH.toLong())
        Snackbar.make(parentLayout!!, "Please Check Internet Connection ", Snackbar.LENGTH_LONG)
            .setAction("CLOSE") { finish() }
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            .setDuration(1000)
            .show()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}