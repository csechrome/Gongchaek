package com.gongchaek.gongchaek.feature.tabmypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ItemBookMyPageBinding
import com.gongchaek.gongchaek.util.ItemBookMyPage


class RecyclerBookMyPageAdapter(private val items: ArrayList<ItemBookMyPage>) :
    RecyclerView.Adapter<RecyclerBookMyPageAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        val listener = View.OnClickListener {
            // TODO
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookMyPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemBookMyPageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: ItemBookMyPage) {
            binding.tvTitle.text = item.title
            binding.ivCover.setImageDrawable(item.cover)
            binding.tvPrice.text = item.price
            binding.tvTerm.text = item.term
            binding.tvBorrowerName.text = item.borrowerName
            binding.ivBorrowerProfile.setImageDrawable(item.borrowerProfile)
            binding.icStatus.setBackgroundResource(R.drawable.item_dot_green) // TODO
            binding.bgStatus.backgroundTintList // TODO
            binding.dueDate.text = item.dueDate
            binding.root.setOnClickListener(listener)
        }
    }
}