package com.gongchaek.gongchaek.feature.tabsearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.databinding.ItemBookNormalBinding
import com.gongchaek.gongchaek.feature.detailpage.DetailPageActivity
import com.gongchaek.gongchaek.util.ItemBookNormal


class RecyclerMyTownBookAdapter(private val items: ArrayList<ItemBookNormal>) :
    RecyclerView.Adapter<RecyclerMyTownBookAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookNormalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    var onItemClick: ((ItemBookNormal) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemBookNormalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemBookNormal) {
            binding.title.text = item.title
            binding.cover.setImageDrawable(item.cover)
            binding.location.text = item.location
            binding.price.text = item.price
            binding.term.text = item.term
            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}