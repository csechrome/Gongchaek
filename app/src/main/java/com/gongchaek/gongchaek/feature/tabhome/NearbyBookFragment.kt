package com.gongchaek.gongchaek.feature.tabhome

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentNearbyBookBinding
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemBookVertical


class NearbyBookFragment : BaseFragment<FragmentNearbyBookBinding>(FragmentNearbyBookBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        

        // TEST
        val bookVerticalList = ArrayList<ItemBookVertical>()
        bookVerticalList.add(ItemBookVertical("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 3"))
        bookVerticalList.add(ItemBookVertical("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 3"))
        bookVerticalList.add(ItemBookVertical("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 3"))
        bookVerticalList.add(ItemBookVertical("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 3"))
        bookVerticalList.add(ItemBookVertical("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "북한산로 3"))

        binding.listNearbyBook.adapter = RecyclerNearbyBookAdapter(bookVerticalList)
    }
}