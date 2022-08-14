package com.gongchaek.gongchaek.feature.tabhome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentNearbyLibraryBinding
import com.gongchaek.gongchaek.feature.detailpage.DetailPageActivity
import com.gongchaek.gongchaek.feature.profile.ProfileActivity
import com.gongchaek.gongchaek.feature.tabsearch.RecyclerMyTownBookAdapter
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemLibrary


class NearbyLibraryFragment : BaseFragment<FragmentNearbyLibraryBinding>(FragmentNearbyLibraryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TEST
        val libraryList = ArrayList<ItemLibrary>()
        libraryList.add(ItemLibrary("박준하", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "스타트업과 경영/경제 도서에 관심이 많습니다.", "북한산로 2", "220"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요", "북한산로 2", "20"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요", "북한산로 2", "20"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요", "북한산로 2", "20"))
        libraryList.add(ItemLibrary("TEST", ContextCompat.getDrawable(requireContext(),
            R.drawable._test
        )!!, "안녕하세요", "북한산로 2", "20"))

        val adapter = RecyclerNearbyLibraryAdapter(libraryList)
        adapter.onItemClick = {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
        binding.listNearbyLibrary.adapter = adapter
    }
}