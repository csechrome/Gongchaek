package com.gongchaek.gongchaek.feature.tabhome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ItemLibrarySmallBinding
import com.gongchaek.gongchaek.feature.main.MainActivity
import com.gongchaek.gongchaek.util.ItemLibrary
import com.gongchaek.gongchaek.feature.profile.ProfileFragment


class RecyclerNearbyLibraryAdapter(private val items: ArrayList<ItemLibrary>) :
    RecyclerView.Adapter<RecyclerNearbyLibraryAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        val listener = View.OnClickListener {
            val fm = (context as MainActivity).supportFragmentManager
            fm.beginTransaction()
                .replace(R.id.layout_view, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLibrarySmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemLibrarySmallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: ItemLibrary) {
            binding.tvName.text = item.name
            binding.ivCover.setImageDrawable(item.cover)
            binding.tvIntroduction.text = item.introduction
            binding.tvBookCount.text = item.bookCount
            binding.tvLocation.text = item.location
            binding.root.setOnClickListener(listener)
        }
    }
}