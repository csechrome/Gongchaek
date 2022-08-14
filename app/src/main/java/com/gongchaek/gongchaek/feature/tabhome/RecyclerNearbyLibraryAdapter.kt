package com.gongchaek.gongchaek.feature.tabhome

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ItemLibrarySmallBinding
import com.gongchaek.gongchaek.feature.main.MainActivity
import com.gongchaek.gongchaek.feature.profile.ProfileActivity
import com.gongchaek.gongchaek.util.ItemBookNormal
import com.gongchaek.gongchaek.util.ItemLibrary


class RecyclerNearbyLibraryAdapter(private val items: ArrayList<ItemLibrary>) :
    RecyclerView.Adapter<RecyclerNearbyLibraryAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLibrarySmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return ViewHolder(binding)
    }

    var onItemClick: ((ItemLibrary) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemLibrarySmallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemLibrary) {
            binding.tvName.text = item.name
            binding.ivCover.setImageDrawable(item.cover)
            binding.tvIntroduction.text = item.introduction
            binding.tvBookCount.text = item.bookCount
            binding.tvLocation.text = item.location
            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}