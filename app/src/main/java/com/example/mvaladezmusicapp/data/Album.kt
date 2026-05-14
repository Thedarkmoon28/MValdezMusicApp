package com.example.mvaladezmusicapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String,
    @SerializedName("image")
    val imageUrl: String
)
