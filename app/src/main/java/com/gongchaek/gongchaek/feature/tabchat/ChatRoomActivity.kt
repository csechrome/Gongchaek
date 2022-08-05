package com.gongchaek.gongchaek.feature.tabchat

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.gongchaek.gongchaek.R
import com.gongchaek.gongchaek.databinding.ActivityChatRoomBinding
import com.gongchaek.gongchaek.global.BaseActivity
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime


class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding>(ActivityChatRoomBinding::inflate) {
    @RequiresApi(Build.VERSION_CODES.O) // TODO: 어떻게 하지
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nickname = pref.getString("nickname", "null")
        val db = FirebaseDatabase.getInstance()
        val ref = db.reference

        binding.etMessage.addTextChangedListener { text ->
            if (text.isNullOrEmpty()) {
                binding.btnSend.isEnabled = false
                binding.btnSend.setImageResource(R.drawable.ic_send_deactivated)
            } else {
                binding.btnSend.isEnabled = true
                binding.btnSend.setImageResource(R.drawable.ic_send_activated)
            }
        }

        binding.btnSend.setOnClickListener {
            // TODO: 전송 로직
            val data = hashMapOf(
                "nickname" to nickname,
                "message" to binding.etMessage.text.toString(),
                "time" to LocalDateTime.now()
            )
            // Firestore에 기록
//            db.collection("Chat").add(data)
//                .addOnSuccessListener {
//                    binding.etMessage.text.clear()
//                }.addOnFailureListener {
//                    showToast("메세지를 전송하는 데 실패했어요")
//                }
        }

        binding.btnState.setOnClickListener {
            // TODO: 액티비티 새로 만들어야 함
        }
    }
}