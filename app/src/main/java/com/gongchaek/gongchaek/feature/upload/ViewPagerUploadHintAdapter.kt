package com.gongchaek.gongchaek.feature.upload

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ItemUploadHintBinding


class HintPagerViewHolder(val binding: ItemUploadHintBinding) : RecyclerView.ViewHolder(binding.root)

class ViewPagerUploadHintAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = imageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = HintPagerViewHolder(
        ItemUploadHintBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as HintPagerViewHolder).binding

        binding.ivHintImage.setImageResource(imageList[position])
        when (position) {
            0 -> binding.tvHintText.setText(R.string.upload_hint_0)
            1 -> binding.tvHintText.setText(R.string.upload_hint_1)
        }
    }
}