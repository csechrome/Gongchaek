package com.gongchaek.gongchaek.model.db.dto

import com.google.gson.annotations.SerializedName


data class ObjectDetectionResponseDTO(
    val predictions: List<Prediction>
)

data class Prediction(
    @SerializedName("num_detections") val numDetections: Int,
    @SerializedName("detection_classes") val detectionClasses: List<Double>,
    @SerializedName("detection_names") val detectionNames: List<String>,
    @SerializedName("detection_scores") val detectionScores: List<Double>,
    @SerializedName("detection_boxes") val detectionBoxes: List<List<Double>>,
)