package com.example.Final_Project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Final_Project.databinding.BrowseByCategoryViewBinding
import com.example.Final_Project.domain.model.BrowseByCategory


typealias onClicked2 = (v: BrowseByCategory) -> Unit

class BrowseByCategoryAdapter :
    ListAdapter<BrowseByCategory, BrowseByCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    lateinit var onClicked2: onClicked2
    lateinit var categortText: String

    class ViewHolder(val binding: BrowseByCategoryViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BrowseByCategory>() {
            override fun areItemsTheSame(
                oldItem: BrowseByCategory,
                newItem: BrowseByCategory
            ): Boolean {
                return oldItem.strMealThumb == newItem.strMealThumb
            }

            override fun areContentsTheSame(
                oldItem: BrowseByCategory,
                newItem: BrowseByCategory
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            BrowseByCategoryViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mealsItem = getItem(position)
        holder.binding.text22.text = mealsItem.strMeal
        holder.binding.text21.text = categortText

        Glide.with(holder.binding.imageView)
            .load(mealsItem.strMealThumb)
            .into(holder.binding.imageView)

        holder.binding.card2.setOnClickListener {
            onClicked2(mealsItem)
        }
    }
}