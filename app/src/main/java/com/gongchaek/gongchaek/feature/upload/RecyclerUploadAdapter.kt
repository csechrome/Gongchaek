package com.gongchaek.gongchaek.feature.upload

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.databinding.ItemUploadPhotoBinding


class RecyclerUploadPhotoAdapter(private val items: ArrayList<Bitmap>) :
    RecyclerView.Adapter<RecyclerUploadPhotoAdapter.ViewHolder>() {

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
        val binding =
            ItemUploadPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemUploadPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Bitmap) {
            binding.ivUploadedPhoto.setImageBitmap(item)
            binding.root.setOnClickListener(listener)
        }
    }
}