package com.gongchaek.gongchaek.feature.tabhome

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.databinding.ItemBookVerticalBinding
import com.gongchaek.gongchaek.feature.detailpage.DetailPageActivity
import com.gongchaek.gongchaek.util.ItemBookVertical


class RecyclerNearbyBookAdapter(private val items: ArrayList<ItemBookVertical>) :
    RecyclerView.Adapter<RecyclerNearbyBookAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        val listener = View.OnClickListener {
            this.context!!.startActivity(Intent(this.context, DetailPageActivity::class.java))
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemBookVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: ItemBookVertical) {
            binding.tvTitle.text = item.title
            binding.ivCover.setImageDrawable(item.cover)
            binding.tvLocation.text = item.location
            binding.root.setOnClickListener(listener)
        }
    }
}