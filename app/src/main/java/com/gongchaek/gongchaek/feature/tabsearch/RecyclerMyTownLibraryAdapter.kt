package com.gongchaek.gongchaek.feature.tabsearch

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.databinding.ItemLibraryBigBinding
import com.gongchaek.gongchaek.util.ItemLibrary


class RecyclerMyTownLibraryAdapter(private val items: ArrayList<ItemLibrary>) :
    RecyclerView.Adapter<RecyclerMyTownLibraryAdapter.ViewHolder>() {

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
        val binding = ItemLibraryBigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return ViewHolder(binding)
    }

    var onItemClick: ((ItemLibrary) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemLibraryBigBinding) :
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