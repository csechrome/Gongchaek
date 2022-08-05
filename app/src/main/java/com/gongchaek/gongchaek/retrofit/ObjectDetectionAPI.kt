package com.gongchaek.gongchaek.retrofit

import com.gongchaek.gongchaek.model.db.dto.ObjectDetectionResponseDTO
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.Multipart


interface ObjectDetectionAPI {
    @Headers(
        "X-NCP-APIGW-API-KEY-ID: 58zycskp7y",
        "X-NCP-APIGW-API-KEY: OuvYuorGXRCbgjOyGuvhVUjMi9CLf9QMWuwDsPx0",
        "Content-Type: multipart/form-data"
    )
    @Multipart
    @POST("vision-obj/v1/detect")
    fun addImage(
        @Part image : MultipartBody.Part
    ): Call<ObjectDetectionResponseDTO>
}
