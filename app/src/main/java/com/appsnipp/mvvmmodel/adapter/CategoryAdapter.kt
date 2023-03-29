package com.appsnipp.mvvmmodel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.adapters.CardViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appsnipp.mvvmmodel.R
import com.appsnipp.mvvmmodel.databinding.ActivityMainBinding.inflate
import com.appsnipp.mvvmmodel.databinding.CateitemlayoutBinding
import com.appsnipp.mvvmmodel.model.Category
import com.appsnipp.mvvmmodel.model.CategoryListModel
import com.bumptech.glide.Glide
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter

class CategoryAdapter(val context:Context,val categoryList:MutableList<Category>) :RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder(CateitemlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.catname.text=categoryList[position].name
       Glide.with(context).load(categoryList[position].mobile_logo).into(holder.binding.image)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class ViewHolder(val binding:CateitemlayoutBinding) : RecyclerView.ViewHolder(binding.root)

}