package com.gongchaek.gongchaek.feature.upload

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ItemUploadManualBinding


class ManualPagerViewHolder(val binding: ItemUploadManualBinding) : RecyclerView.ViewHolder(binding.root)

class ViewPagerUploadManualAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = imageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ManualPagerViewHolder(
        ItemUploadManualBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ManualPagerViewHolder).binding

        binding.ivManualImage.setImageResource(imageList[position])
        when (position) {
            0 -> binding.tvManualText.setText(R.string.upload_manual_0)
            1 -> binding.tvManualText.setText(R.string.upload_manual_1)
            2 -> binding.tvManualText.setText(R.string.upload_manual_2)
        }
    }
}