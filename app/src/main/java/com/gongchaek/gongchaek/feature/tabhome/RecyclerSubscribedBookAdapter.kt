package com.gongchaek.gongchaek.feature.tabhome

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.databinding.ItemBookNormalBinding
import com.gongchaek.gongchaek.feature.detailpage.DetailPageActivity
import com.gongchaek.gongchaek.util.ItemBookNormal


class RecyclerSubscribedBookAdapter(private val items: ArrayList<ItemBookNormal>) :
    RecyclerView.Adapter<RecyclerSubscribedBookAdapter.ViewHolder>() {

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
        val binding = ItemBookNormalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemBookNormalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: ItemBookNormal) {
            binding.title.text = item.title
            binding.cover.setImageDrawable(item.cover)
            binding.location.text = item.location
            binding.price.text = item.price
            binding.term.text = item.term
            binding.root.setOnClickListener(listener)
        }
    }
}