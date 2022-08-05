package com.gongchaek.gongchaek.feature.tabhome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.FragmentTabHomeBinding
import com.gongchaek.gongchaek.feature.notification.NotificationActivity
import com.gongchaek.gongchaek.feature.search.SearchActivity
import com.gongchaek.gongchaek.global.BaseFragment
import com.gongchaek.gongchaek.util.ItemBookNormal
import com.gongchaek.gongchaek.util.showNetworkErrorDialog
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class TabHomeFragment : BaseFragment<FragmentTabHomeBinding>(FragmentTabHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(R.id.layout_nearby, NearbyLibraryFragment())
            .commit()

        val user = Firebase.auth.currentUser!!
        val nickname = user.displayName!!

        val db = FirebaseDatabase.getInstance()
        val ref = db.reference.child("user").child(nickname.lowercase())

        // Update User Info
        pref.setString("nickname", nickname)
        ref.get().addOnSuccessListener { DataSnapshot ->
            pref.setString("location", DataSnapshot.child("location").value.toString())
            pref.setString("region", DataSnapshot.child("region").value.toString())
            pref.setString("introduction", DataSnapshot.child("introduction").value.toString())
            pref.setString("interest", DataSnapshot.child("interest").value.toString())
            pref.setString("follower", DataSnapshot.child("follower").value.toString())
            pref.setString("following", DataSnapshot.child("following").value.toString())
        }.addOnFailureListener {
            requireContext().showNetworkErrorDialog()
        }

        binding.tvName.text = "${nickname}님의 독서 취향을"

        binding.tabNearby.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = childFragmentManager.beginTransaction()
                when (tab!!.position) {
                    0 -> transaction.replace(R.id.layout_nearby, NearbyLibraryFragment()).commit()
                    1 -> transaction.replace(R.id.layout_nearby, NearbyBookFragment()).commit()
                }
            }
        })

        binding.btnSearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }

        binding.btnNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }

        // TEST
        val bookNormalList = ArrayList<ItemBookNormal>()
        bookNormalList.add(
            ItemBookNormal(
                "TEST", ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable._test
                )!!, "북한산로 2", "1,000원", "2주"
            )
        )
        bookNormalList.add(
            ItemBookNormal(
                "TEST", ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable._test
                )!!, "북한산로 2", "1,000원", "2주"
            )
        )
        bookNormalList.add(
            ItemBookNormal(
                "TEST", ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable._test
                )!!, "북한산로 2", "1,000원", "2주"
            )
        )

        binding.listSubscribed.adapter = RecyclerSubscribedBookAdapter(bookNormalList)
    }
}